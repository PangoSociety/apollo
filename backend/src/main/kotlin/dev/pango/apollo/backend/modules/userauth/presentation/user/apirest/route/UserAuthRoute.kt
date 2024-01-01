package dev.pango.apollo.backend.modules.userauth.presentation.user.apirest.route

import dev.pango.apollo.backend.framework.http.getLocale
import dev.pango.apollo.backend.framework.http.respondEither
import dev.pango.apollo.backend.modules.sharedkernel.presentation.apirest.config.ApiRestConstants
import dev.pango.apollo.backend.modules.userauth.presentation.user.apirest.config.AUTH
import dev.pango.apollo.backend.modules.userauth.presentation.user.apirest.dto.RefreshTokenDTO
import dev.pango.apollo.backend.modules.userauth.presentation.user.apirest.facade.UserServiceFacade
import dev.pango.apollo.backend.modules.userauth.presentation.user.translation.toTranslatableFailure
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.routing.*
import io.ktor.server.routing.Route
import org.koin.ktor.ext.inject

fun Route.userAuthRoute() {
    val userServiceFacade by inject<UserServiceFacade>()
    route("/${ApiRestConstants.Resources.AUTH}") {
        post {
            val locale = call.request.getLocale()
            val loginUser = userServiceFacade.loginUser(call.receive())
            val userLoginEitherTransformed =
                loginUser.mapLeft { it.toTranslatableFailure(locale) }
            call.respondEither(HttpStatusCode.OK, userLoginEitherTransformed)
        }

        post("/refresh") {
//        val request = call.receive<RefreshTokenDTO>()
            val locale = call.request.getLocale()
            val newAccessToken = userServiceFacade.accessUser(call.receive<RefreshTokenDTO>().token)
            val newAuthTokenEitherTransformed =
                newAccessToken.mapLeft { it.toTranslatableFailure(locale) }
            call.respondEither(HttpStatusCode.OK, newAuthTokenEitherTransformed)
//        newAccessToken.fold(
//            ifLeft = {
//                call.respond(HttpStatusCode.BadRequest, ErrorResponse(it.errorMessage))
//            },
//            ifRight = {
//                call.respond(
//                    it.toRefreshDTO()
//                )
//            }
//        )
        }
    }
}
