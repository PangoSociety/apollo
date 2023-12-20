package dev.pango.apollo.backend.modules.userauth.presentation.apirest

import dev.pango.apollo.backend.modules.userauth.data.dto.user.*
import dev.pango.apollo.backend.modules.userauth.data.repository.*
import dev.pango.apollo.backend.modules.userauth.domain.entity.UserEntity
import dev.pango.apollo.backend.modules.userauth.domain.repository.*
import dev.pango.apollo.backend.modules.userauth.infraestructure.persistence.tables.User
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.*

fun Application.configureUserRoutes() {
    val user by inject<UserRepository>()
    routing {
        route("/v1/users") {
            createUser(user)
            deleteUser(user)
            searchUser(user)
            updateUser(user)
            getAllUser(user)
        }
    }
}

fun Route.createUser(userRepository: UserRepository) {
    post {
        try {
            val entity = call.receive<AddUserDTO>()
            val success =
                userRepository.createUser(
                    firstname = entity.firstName,
                    lastname = entity.lastName,
                    email = entity.email,
                )

            success.fold(
                ifLeft = {
                    call.respond(HttpStatusCode.BadRequest, ErrorResponse(it.errorMessage))
                },
                ifRight = {
                    call.respond(HttpStatusCode.Created)
                },
            )
        } catch (e: Exception) {
            call.respond(HttpStatusCode.BadRequest, ErrorResponse(e.message ?: "unexpected"))
        }
    }
}

fun Route.deleteUser(userRepository: UserRepository) {
    delete("/{id}") {
        try {
            val id: Int = call.parameters["id"]?.toIntOrNull()!!
            val success = userRepository.deleteUser(id)
            success.fold(
                ifLeft = {
                    call.respond(
                        HttpStatusCode.BadRequest,
                        ErrorResponse("Cannot delete user with id [$id]"),
                    )
                },
                ifRight = {
                    call.respond(HttpStatusCode.NoContent, message = "User with id [$id] deleted!!")
                },
            )
        } catch (e: Exception) {
            call.respond(HttpStatusCode.BadRequest, ErrorResponse(e.message ?: "unexpected"))
        }
    }
}

fun Route.updateUser(userRepository: UserRepository) {
    patch("/{id}") {
        try {
            val entity = call.receive<UpdateUserDTO>()
            val id: Int = call.parameters["id"]?.toIntOrNull()!!
            val success = userRepository.updateUser(id = id, firstname = entity.firstName)
            success.fold(
                ifLeft = {
                    call.respond(
                        HttpStatusCode.BadRequest,
                        call.respond(
                            HttpStatusCode.BadRequest,
                            ErrorResponse("Cannot update user with id [$id]"),
                        ),
                    )
                },
                ifRight = {
                    call.respond(HttpStatusCode.NoContent, message = "User with id [$id] updated!!")
                },
            )
        } catch (e: Exception) {
            call.respond(HttpStatusCode.BadRequest, ErrorResponse(e.message ?: "unexpected"))
        }
    }
}

fun Route.searchUser(userRepository: UserRepository) {
    get("/{id}") {
        try {
            val id: Int = call.parameters["id"]?.toIntOrNull()!!
            val success = userRepository.searchUser(id = id)
            userRepository.searchUser(id)
                ?.let { foundUser -> foundUser.toUserResponse() }
                ?.let { response -> call.respond(response) }
                ?: return@get call.respond(
                    HttpStatusCode.BadRequest,
                    ErrorResponse("User with id [$id] not found"),
                )
        } catch (e: Exception) {
            call.respond(HttpStatusCode.BadRequest, ErrorResponse(e.message ?: "unexpected"))
        }
    }
}


fun Route.getAllUser(userRepository: UserRepository) {
    get {
        val books = userRepository.findAllUsers()
            .map(User::toUserResponse)
        call.respond(message = books)
    }
}

private fun User?.toUserResponse(): UserEntity? =
    this?.let { UserEntity(it.id!!, it.firstName, it.lastName, it.email) }