package com.example.modules.userauth.presentation.apirest

import com.example.models.*
import com.example.modules.userauth.data.dto.user.AddUserDTO
import com.example.modules.userauth.data.dto.user.UpdateUserDTO
import com.example.modules.userauth.data.dto.user.toUserDomain
import com.example.modules.userauth.domain.entity.DeleteUserEntity
import com.example.modules.userauth.domain.repository.UserRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject


fun Application.configureUserRoutes() {
    val user by inject<UserRepository>()
    routing {
        route("/create") {
            createUser(user)
        }
        route("/delete") {
            deleteUser(user)
        }
        route("/search") {
            searchUser(user)
        }
        route("/update") {
            updateUser(user)
        }

    }
}

fun Route.createUser(userRepository: UserRepository) {
    post {
        try {
            val entity = call.receive<AddUserDTO>()

            val success = userRepository.createUser2(userEntity = entity.toUserDomain())

            success.fold(
                ifLeft = {

                    call.respond(HttpStatusCode.BadRequest, ErrorResponse(it.errorMessage))

                },
                ifRight = {
                    call.respond(HttpStatusCode.Created)
                }
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
            val success = userRepository.deleteUser(DeleteUserEntity(id))
            success.fold(
                ifLeft = {
                    call.respond(
                        HttpStatusCode.BadRequest,
                        ErrorResponse("Cannot delete user with id [$id]")
                    )
                },
                ifRight = {
                    call.respond(HttpStatusCode.NoContent, message = "User with id [$id] deleted!!")

                }
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
            val success = userRepository.updateUser(id = id, userEntity = entity.toUserDomain())
            success.fold(
                ifLeft = {
                    call.respond(
                        HttpStatusCode.BadRequest,
                        call.respond(
                            HttpStatusCode.BadRequest,
                            ErrorResponse("Cannot update book with id [$id]")
                        )

                    )
                },
                ifRight = {
                    call.respond(HttpStatusCode.NoContent, message = "User with id [$id] updated!!")

                }
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
            success?.firstName
                ?.let { response -> call.respond(response) }
                ?: return@get call.respond(
                    HttpStatusCode.BadRequest,
                    ErrorResponse("Book with id [$id] not found")
                )


        } catch (e: Exception) {
            call.respond(HttpStatusCode.BadRequest, ErrorResponse(e.message ?: "unexpected"))
        }

    }
}

private fun User?.toUserResponse(): UserResponse? =
    this?.let { UserResponse(it.id!!, it.firstName, it.lastName, it.email) }

