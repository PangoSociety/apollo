package dev.pango.apollo.backend.modules.userauth.mapper

import dev.pango.apollo.backend.modules.userauth.aplication.config.JwtConfig
import dev.pango.apollo.backend.modules.userauth.domain.failure.UserDomainFailure
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.jwt.jwt
import io.ktor.server.response.respond

fun Application.configureAuth() {
    install(Authentication) {
        jwt("auth-jwt") {
            realm = JwtConfig.MY_REALM
            verifier(JwtConfig.verifier)
            validate { credential ->
                val userId = credential.payload.getClaim("id").asString()
                if (userId != "") {
                    JWTPrincipal(credential.payload)
                } else {
                    null
                }
            }
            challenge { defaultScheme, realm ->
                call.respond(HttpStatusCode.Unauthorized, UserDomainFailure.TokenNotFoundOrExpiredNotAvailable)
            }
        }
    }
}
