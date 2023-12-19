package dev.pango.routes

import JwtConfig
import dev.pango.dao.*
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
            val UserService = dev.pango.dao.UserService()
            createUser(UserService)
            getAllUsersRoute(UserService)
        }
        authenticate("auth-jwt") {
            get("/hello") {
                val principal = call.principal<JWTPrincipal>()
                val firstname = principal!!.payload.getClaim("firstName").asString()
                val expiresAt = principal.expiresAt?.time?.minus(System.currentTimeMillis())
                call.respondText("Hello, $firstname! El token expiran en $expiresAt ms.")
            }
        }

        post("/login") {
            val loginRequest = call.receive<UserRequest>()
            val userService = dev.pango.dao.UserService()
            val user =
                userService.getUserByCredentials(loginRequest.firstName, loginRequest.lastName, loginRequest.email)
            if (user != null) {
                val token = JwtConfig.makeToken(user.id.toString(), user.firstName)
                call.respond(hashMapOf("token" to token))
            } else {
                call.respond(HttpStatusCode.Unauthorized, "Credenciales inválidas")
            }

//            val token = JWT.create()
//                .withAudience(audience)
//                .withIssuer(issuer)
//                .withClaim("id", "eddy")
//                .withExpiresAt(Date(System.currentTimeMillis() + 60000))
//                .sign(Algorithm.HMAC256(secret))
//            call.respond(hashMapOf("token" to token))
        }
    }

}

fun Route.getAllUsersRoute(userService: dev.pango.dao.UserService) {
    get {
        val users = userService.findAllUsers()
            .map(User::toUserResponse)
        call.respond(message = users)
    }
}

fun Route.createUser(userService: dev.pango.dao.UserService) {
    post {
        val request = call.receive<UserRequest>()

        val success = userService.createUser(userRequest = request)

        if (success)
            call.respond(HttpStatusCode.Created)
        else
            call.respond(HttpStatusCode.BadRequest, ErrorResponse("Cannot create User"))
    }
}

private fun User?.toUserResponse(): UserResponse? =
    this?.let { UserResponse(it.id!!, it.firstName, it.lastName, it.email) }