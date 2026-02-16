package com.vrs.v1.resource

import com.vrs.v1.entity.Role
import com.vrs.v1.service.RoleService
import io.smallrye.mutiny.Uni
import jakarta.ws.rs.DELETE
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.hibernate.reactive.mutiny.Mutiny

@Path("/roles")
@Produces(MediaType.APPLICATION_JSON)
class RoleResource(private val roleService: RoleService) {

    @GET
    fun listAll(): Uni<List<Role>> = roleService.listAll()

    @POST
    fun add(role: Role): Uni<Response> {
        return Mutiny.fetch(role)
            .flatMap { roleService.add(role) }
            .map { Response.status(Response.Status.CREATED).entity(it).build() }
    }

    @DELETE
    @Path("/{id}")
    fun delete(@PathParam("id") id: Long): Uni<Response> {
        return roleService.deleteById(id)
            .map { deleted ->
                if (deleted) {
                    Response.noContent().build()
                } else {
                    Response.status(Response.Status.NOT_FOUND).build()
                }
            }
    }
}
