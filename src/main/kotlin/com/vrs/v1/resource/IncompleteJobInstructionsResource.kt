package com.vrs.v1.resource

import com.vrs.v1.entity.IncompleteJobInstructions
import com.vrs.v1.service.IncompleteJobInstructionsService
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

@Path("/incomplete-job-instructionss")
@Produces(MediaType.APPLICATION_JSON)
class IncompleteJobInstructionsResource(private val service: IncompleteJobInstructionsService) {

    @GET
    fun listAll(): Uni<List<IncompleteJobInstructions>> = service.listAll()

    @GET
    @Path("/{id}")
    fun getById(@PathParam("id") id: Long): Uni<IncompleteJobInstructions> {
        return service.findById(id)
            .onItem().ifNull().failWith { NotFoundException("IncompleteJobInstructions not found with id: $id") }
    }

    @POST
    fun add(entity: IncompleteJobInstructions): Uni<Response> {
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
