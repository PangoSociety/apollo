package dev.pango.plugins

import JwtConfig
import com.auth0.jwt.*
import com.auth0.jwt.algorithms.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import io.ktor.server.routing.*
import java.util.Date

fun Application.configureAuth() {
    install(Authentication) {
        jwt("auth-jwt") {
            realm = JwtConfig.myRealm
            verifier(JwtConfig.verifier)
            validate { credential ->
                val userId = credential.payload.getClaim("id").asString()
                if (userId != "") {
                    JWTPrincipal(credential.payload)
//                    UserIdPrincipal(userId.toString())
                } else {
                    null
                }
            }
            challenge { defaultScheme, realm ->
                call.respond(HttpStatusCode.Unauthorized, "Token is not valid or has expired")
            }
        }
    }


}