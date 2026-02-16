package com.vrs.v1.resource

import com.vrs.v1.entity.ResolvingAuthority
import com.vrs.v1.service.ResolvingAuthorityService
import io.smallrye.mutiny.Uni
import jakarta.ws.rs.DELETE
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.NotFoundException
import jakarta.ws.rs.core.Response
import org.hibernate.reactive.mutiny.Mutiny

@Path("/resolving-authoritys")
@Produces(MediaType.APPLICATION_JSON)
class ResolvingAuthorityResource(private val service: ResolvingAuthorityService) {

    @GET
    fun listAll(): Uni<List<ResolvingAuthority>> = service.listAll()

    @GET
    @Path("/{id}")
    fun getById(@PathParam("id") id: Long): Uni<ResolvingAuthority> {
        return service.findById(id)
            .onItem().ifNull().failWith { NotFoundException("ResolvingAuthority not found with id: $id") }
    }

    @POST
    fun add(entity: ResolvingAuthority): Uni<Response> {
        return Mutiny.fetch(entity)
            .flatMap { service.add(entity) }
            .map { Response.status(Response.Status.CREATED).entity(it).build() }
    }

    @DELETE
    @Path("/{id}")
    fun delete(@PathParam("id") id: Long): Uni<Response> {
        return service.deleteById(id)
            .map { deleted ->
                if (deleted) {
                    Response.noContent().build()
                } else {
                    Response.status(Response.Status.NOT_FOUND).build()
                }
            }
    }
}
