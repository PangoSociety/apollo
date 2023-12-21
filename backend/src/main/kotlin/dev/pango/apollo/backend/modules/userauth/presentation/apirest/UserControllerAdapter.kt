package dev.pango.apollo.backend.modules.userauth.presentation.apirest

import dev.pango.apollo.backend.modules.userauth.data.dto.user.*
import dev.pango.apollo.backend.modules.userauth.data.repository.*
import dev.pango.apollo.backend.modules.userauth.domain.entity.User
import dev.pango.apollo.backend.modules.userauth.domain.repository.*
import dev.pango.apollo.backend.modules.userauth.mapper.toDetailUserDTO
import dev.pango.apollo.backend.modules.userauth.presentation.apivariable.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.*

fun Application.configureUserRoutes() {
    val userRepository by inject<UserRepository>()
    routing {
        route("${ApiRestVersioning.V1}/${ApiRestResources.USERS}") {
            createUser(userRepository)
            deleteUser(userRepository)
            findUser(userRepository)
            updateUser(userRepository)
            getUserList(userRepository)
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
            val success =
                userRepository.updateUser(
                    User(
                        id = id,
                        firstName = entity.firstName,
                        lastName = entity.lastName,
                        email = entity.email,
                    ),
                )
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
                    call.respond(it.toDetailUserDTO())
                },
            )
        } catch (e: Exception) {
            call.respond(HttpStatusCode.BadRequest, ErrorResponse(e.message ?: "unexpected"))
        }
    }
}

fun Route.findUser(userRepository: UserRepository) {
    get("/{id}") {
        try {
            val id: Int = call.parameters["id"]?.toIntOrNull()!!
            val user = userRepository.findById(id)
            user.fold(
                ifLeft = {
                    call.respond(
                        HttpStatusCode.BadRequest,
                        call.respond(
                            HttpStatusCode.BadRequest,
                            ErrorResponse("Cannot find user with id [$id]"),
                        ),
                    )
                },
                ifRight = {
                    call.respond(it.toDetailUserDTO())
                },
            )
        } catch (e: Exception) {
            call.respond(HttpStatusCode.BadRequest, ErrorResponse(e.message ?: "unexpected"))
        }
    }
}

fun Route.getUserList(userRepository: UserRepository) {
    get {
        val users =
            userRepository.findAll()

        users.fold(
            ifLeft = {
                call.respond(
                    HttpStatusCode.BadRequest,
                    call.respond(
                        HttpStatusCode.BadRequest,
                        ErrorResponse("Cannot find users"),
                    ),
                )
            },
            ifRight = {
                call.respond(it.map { user -> user.toDetailUserDTO() })
            },
        )
        call.respond(message = users)
    }
}
