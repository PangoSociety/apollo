package dev.pango.routes

import JwtConfig
import dev.pango.models.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.server.routing.*


fun Application.configureUserRoutess() {
    routing {

        route("/users") {

        }
        authenticate("auth-jwt") {
            get("/hello") {
                val principal = call.principal<JWTPrincipal>()
                val firstname = principal!!.payload.getClaim("firstName").asString()
                val expiresAt = principal.expiresAt?.time?.minus(System.currentTimeMillis())
                call.respondText("Hello, $firstname! El token expiran en $expiresAt ms.")
            }
        }


    }

}




