import os
import xml.etree.ElementTree as ET
import re

def to_camel_case(snake_str):
    if not snake_str:
        return ""
    # Handle both underscores and spaces
    components = re.split(r'[_ ]', snake_str)
    return ''.join(x.capitalize() for x in components)

def to_kotlin_type(ftype, precision, scale):
    if ftype == 'String':
        return 'String'
    elif ftype == 'Number':
        if scale and int(scale) > 0:
            return 'java.math.BigDecimal'
        elif precision and int(precision) > 9:
            return 'Long'
        else:
            return 'Int'
    elif ftype == 'Date':
        return 'java.time.LocalDate'
    elif ftype == 'DateTime':
        return 'java.time.LocalDateTime'
    else:
        return 'String'

def parse_component_properties(file_path):
    if not os.path.exists(file_path):
        print(f"Warning: File not found {file_path}")
        return []
    
    try:
        tree = ET.parse(file_path)
        root = tree.getroot()
        fields = []
        for labelfield in root.findall('.//labelfield'):
            fname = labelfield.get('fname')
            ftype = labelfield.get('ftype')
            nullable = labelfield.get('nullable') == 'yes'
            precision = labelfield.get('precision')
            scale = labelfield.get('scale')
            if fname:
                fields.append({
                    'name': fname,
                    'type': to_kotlin_type(ftype, precision, scale),
                    'nullable': nullable,
                    'precision': precision,
                    'scale': scale
                })
        return fields
    except Exception as e:
        print(f"Error parsing {file_path}: {e}")
        return []

def main():
    base_dir = 'src/main/resources'
    serviceforms_dir = os.path.join(base_dir, 'com/atkins/services/smcjobinstructions/serviceforms')
    output_dir = 'src/main/kotlin/com/vrs/v1/entity'
    
    if not os.path.exists(output_dir):
        os.makedirs(output_dir)

    service_files = []
    for f in os.listdir(serviceforms_dir):
        if f.endswith('.xml'):
            service_files.append(f)

    components = {}

    for service_file in service_files:
        file_path = os.path.join(serviceforms_dir, service_file)
        tree = ET.parse(file_path)
        root = tree.getroot()
        if root.tag != 'ServiceForm':
            continue
        
        for sc in root.findall('.//servicecomponent'):
            is_view = sc.get('isView')
            if is_view and is_view.lower() == 'yes':
                continue
            
            table = sc.get('table')
            if not table:
                continue
            
            table_name = table.split('.')[-1]
            idref = sc.get('idref')
            comp_prop_fn = sc.get('componentpropertiesfn')
            masteridref = sc.get('masteridref')
            
            comp_id = table_name + "_" + idref
            
            if comp_id not in components:
                components[comp_id] = {
                    'table_name': table_name,
                    'idref': idref,
                    'comp_prop_fn': comp_prop_fn,
                    'masteridref': masteridref,
                    'fields': [],
                    'children': []
                }
            
            if comp_prop_fn:
                prop_path = os.path.join(base_dir, comp_prop_fn.lstrip('/'))
                fields = parse_component_properties(prop_path)
                existing_fnames = {f['name'] for f in components[comp_id]['fields']}
                for f in fields:
                    if f['name'] not in existing_fnames:
                        components[comp_id]['fields'].append(f)

    # Establish relationships
    for comp_id, comp in components.items():
        if comp['masteridref'] and comp['masteridref'] != "0":
            found_parent = False
            # Search for parent in components from the same service form logic
            # (In this case components is already a global map, so we search all)
            for parent_id, parent_comp in components.items():
                if parent_comp['idref'] == comp['masteridref']:
                    # Ensure we don't link to ourselves
                    if comp_id != parent_id:
                        comp['parent'] = parent_id
                        found_parent = True
                        break
    
    # After identifying all parents, populate children
    for comp_id, comp in components.items():
        if 'parent' in comp:
            parent_id = comp['parent']
            if comp_id not in components[parent_id]['children']:
                components[parent_id]['children'].append(comp_id)

    # Merge components by table name
    table_to_comp = {}
    for comp_id, comp in components.items():
        table_name = comp['table_name']
        if table_name not in table_to_comp:
            table_to_comp[table_name] = {
                'table_name': table_name,
                'idref': comp['idref'],
                'fields': comp['fields'][:],
                'children_tables': set(),
                'all_parent_tables': set(),
                'all_masteridrefs': set()
            }
        else:
            if not table_to_comp[table_name]['idref'] and comp['idref']:
                table_to_comp[table_name]['idref'] = comp['idref']
            
            existing_fnames = {f['name'] for f in table_to_comp[table_name]['fields']}
            for f in comp['fields']:
                if f['name'] not in existing_fnames:
                    table_to_comp[table_name]['fields'].append(f)
        
        # Collect all masteridrefs for this table
        if comp['masteridref'] and comp['masteridref'] != "0":
            table_to_comp[table_name]['all_masteridrefs'].add(comp['masteridref'])
            # Search for parent across all components
            for p_id, p_comp in components.items():
                if p_comp['idref'] == comp['masteridref']:
                     table_to_comp[table_name]['all_parent_tables'].add(p_comp['table_name'])
    
    # Second pass to populate children_tables
    for table_name, info in table_to_comp.items():
        for p_table in info['all_parent_tables']:
            if p_table in table_to_comp:
                table_to_comp[p_table]['children_tables'].add(table_name)

    for table_name, comp in table_to_comp.items():
        class_name = to_camel_case(table_name)
        file_path = os.path.join(output_dir, f"{class_name}.kt")
        
        with open(file_path, 'w') as f:
            f.write("package com.vrs.v1.entity\n\n")
            f.write("import jakarta.persistence.*\n")
            f.write("import java.time.LocalDateTime\n")
            f.write("import java.time.LocalDate\n\n")
            f.write("@Entity\n")
            f.write(f"@Table(name = \"{comp['table_name']}\")\n")
            f.write(f"class {class_name} {{\n")
            
            # Fields
            fields = comp['fields']
            id_field_name = comp['idref']
            
            field_names = [field['name'] for field in fields]
            if id_field_name and id_field_name not in field_names:
                fields.append({'name': id_field_name, 'type': 'Long', 'nullable': False, 'precision': '10', 'scale': None})

            # Reorder fields
            id_fields = [fi for fi in fields if fi['name'] == id_field_name]
            other_fields = [fi for fi in fields if fi['name'] != id_field_name]
            ordered_fields = id_fields + other_fields

            for field in ordered_fields:
                is_id = field['name'] == id_field_name
                is_fk = False
                parent_table_for_fk = None
                
                # Check all_masteridrefs to see if this field is a foreign key
                if field['name'] in comp['all_masteridrefs']:
                    # Find which parent has this idref
                    for p_table in comp['all_parent_tables']:
                        if p_table in table_to_comp and table_to_comp[p_table]['idref'] == field['name']:
                            is_fk = True
                            parent_table_for_fk = p_table
                            break
                
                if is_id:
                    f.write("    @Id\n")
                
                if is_fk:
                    parent_class = to_camel_case(parent_table_for_fk)
                    f.write(f"    @ManyToOne(fetch = FetchType.LAZY)\n")
                    f.write(f"    @JoinColumn(name = \"{field['name']}\")\n")
                    prop_name = parent_class[0].lower() + parent_class[1:]
                    f.write(f"    var {prop_name}: {parent_class}? = null\n\n")
                else:
                    k_type = field['type']
                    col_ann = f"    @Column(name = \"{field['name']}\""
                    if not field['nullable']:
                        col_ann += ", nullable = false"
                    
                    if k_type == 'java.math.BigDecimal':
                        if field['precision']:
                            col_ann += f", precision = {field['precision']}"
                        if field['scale']:
                            col_ann += f", scale = {field['scale']}"
                    elif k_type in ['Int', 'Long']:
                        if field['precision']:
                             col_ann += f", precision = {field['precision']}"
                    
                    col_ann += ")\n"
                    f.write(col_ann)
                    
                    prop_name = field['name'].lower()
                    if field['nullable'] or is_id:
                        f.write(f"    var {prop_name}: {k_type}? = null\n\n")
                    else:
                        if k_type == 'String':
                            f.write(f"    lateinit var {prop_name}: {k_type}\n\n")
                        else:
                            f.write(f"    var {prop_name}: {k_type}? = null\n\n")
            
            # OneToMany children
            for child_table in sorted(list(comp['children_tables'])):
                child_class = to_camel_case(child_table)
                child_prop = child_class[0].lower() + child_class[1:] + "s"
                f.write(f"    @OneToMany(mappedBy = \"{class_name[0].lower() + class_name[1:]}\", cascade = [CascadeType.ALL])\n")
                f.write(f"    var {child_prop}: MutableList<{child_class}> = mutableListOf()\n\n")

            f.write("}\n")

if __name__ == "__main__":
    main()
