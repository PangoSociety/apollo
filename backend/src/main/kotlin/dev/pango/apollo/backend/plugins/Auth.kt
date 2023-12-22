//package dev.pango.apollo.backend.plugins
//
//import JwtConfig
//import io.ktor.http.*
//import io.ktor.server.application.*
//import io.ktor.server.auth.*
//import io.ktor.server.auth.jwt.*
//import io.ktor.server.response.*
//
//fun Application.configureAuth() {
//    install(Authentication) {
//        jwt("auth-jwt") {
//            realm = JwtConfig.MY_REALM
//            verifier(JwtConfig.verifier)
//            validate { credential ->
//                val userId = credential.payload.getClaim("id").asString()
//                if (userId != "") {
//                    JWTPrincipal(credential.payload)
////                    UserIdPrincipal(userId.toString())
//                } else {
//                    null
//                }
//            }
//            challenge { defaultScheme, realm ->
//                call.respond(HttpStatusCode.Unauthorized, "Token is not valid or has expired")
//            }
//        }
//    }
//}
