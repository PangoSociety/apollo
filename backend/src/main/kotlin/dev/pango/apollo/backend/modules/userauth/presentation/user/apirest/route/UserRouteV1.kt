package dev.pango.apollo.backend.modules.userauth.presentation.user.apirest.route

import dev.pango.apollo.backend.framework.http.getLocale
import dev.pango.apollo.backend.framework.http.respondEither
import dev.pango.apollo.backend.modules.educational.presentation.course.translation.toTranslatableFailure
import dev.pango.apollo.backend.modules.sharedkernel.presentation.apirest.config.ApiRestConstants
import dev.pango.apollo.backend.modules.userauth.domain.entity.User
import dev.pango.apollo.backend.modules.userauth.presentation.user.apirest.config.USERS
import dev.pango.apollo.backend.modules.userauth.presentation.user.apirest.dto.UpdateUserDTO
import dev.pango.apollo.backend.modules.userauth.presentation.user.apirest.facade.UserServiceFacade
import dev.pango.apollo.backend.modules.userauth.presentation.user.translation.toTranslatableFailure
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.auth.authenticate
import io.ktor.server.request.receive
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.patch
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import org.koin.ktor.ext.inject
import java.util.UUID

fun Route.userRoutesV1() {
    val userServiceFacade by inject<UserServiceFacade>()
    route("/${ApiRestConstants.Resources.USERS}") {
        authenticate("auth-jwt") {
            get {
                val locale = call.request.getLocale()
                val users = userServiceFacade.listAllUsers()
                val userListEitherTransformed =
                    users.mapLeft {
                        it.toTranslatableFailure(locale)
                    }
                call.respondEither(HttpStatusCode.Found, userListEitherTransformed)
            }

            get("/{id}") {
                val idString: String? = call.parameters["id"]
                val idUUID: UUID = UUID.fromString(idString)
                val locale = call.request.getLocale()
                val user = userServiceFacade.listUserById(idUUID)
                val userByIdEitherTransformed =
                    user.mapLeft {
                        it.toTranslatableFailure(locale)
                    }
                call.respondEither(HttpStatusCode.Found, userByIdEitherTransformed)
            }
            post {
                val locale = call.request.getLocale()
                val userCreated = userServiceFacade.createUser(call.receive())
                val userCreatedEitherTransformed =
                    userCreated.mapLeft { it.toTranslatableFailure(locale) }
                call.respondEither(HttpStatusCode.Created, userCreatedEitherTransformed)
            }

            delete("/{id}") {
                val idString: String? = call.parameters["id"]
                val idUUID: UUID = UUID.fromString(idString)
                val locale = call.request.getLocale()
                val userDelete = userServiceFacade.deleteUser(idUUID)
                val userDeleteEitherTransformed =
                    userDelete.mapLeft {
                        it.toTranslatableFailure(locale)
                    }
                call.respondEither(HttpStatusCode.NoContent, userDeleteEitherTransformed)
            }

            patch("/{id}") {
                val idString: String? = call.parameters["id"]
                val idUUID: UUID = UUID.fromString(idString)
                val entity = call.receive<UpdateUserDTO>()
                val locale = call.request.getLocale()
                val userUpdate =
                    userServiceFacade.updateUser(
                        User(
                            id = idUUID,
                            entity.username,
                            entity.firstname,
                            entity.lastname,
                            entity.email,
                            entity.password,
                        ),
                    )
                val userUpdateEitherTransformed =
                    userUpdate.mapLeft {
                        it.toTranslatableFailure(locale)
                    }
                call.respondEither(HttpStatusCode.OK, userUpdateEitherTransformed)
            }
        }
    }
}
