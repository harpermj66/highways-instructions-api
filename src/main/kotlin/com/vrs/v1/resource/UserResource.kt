package com.vrs.v1.resource

import com.vrs.v1.entity.User
import com.vrs.v1.service.UserService
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

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
class UserResource(private val userService: UserService) {

    @GET
    fun listAll(): Uni<List<User>> = userService.listAll()

    @GET
    @Path("/{id}")
    fun getById(@PathParam("id") id: Long): Uni<User> {
        return userService.findById(id)
            .onItem().ifNull().failWith { NotFoundException("User not found with id: $id") }
    }

    @POST
    fun add(user: User): Uni<Response> {
        return Mutiny.fetch(user)
            .flatMap { userService.add(user) }
            .map { Response.status(Response.Status.CREATED).entity(it).build() }
    }

    @DELETE
    @Path("/{id}")
    fun delete(@PathParam("id") id: Long): Uni<Response> {
        return userService.deleteById(id)
            .map { deleted ->
                if (deleted) {
                    Response.noContent().build()
                } else {
                    Response.status(Response.Status.NOT_FOUND).build()
                }
            }
    }
}
