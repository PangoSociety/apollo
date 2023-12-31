//package dev.pango.apollo.backend.modules.userauth.presentation.user.apirest
//
//import dev.pango.apollo.backend.modules.userauth.domain.repository.*
//import dev.pango.apollo.backend.modules.userauth.presentation.apivariable.*
//import io.ktor.server.application.*
//import io.ktor.server.auth.authenticate
//import io.ktor.server.auth.jwt.JWTPrincipal
//import io.ktor.server.auth.principal
//import io.ktor.server.response.*
//import io.ktor.server.routing.*
//import org.koin.ktor.ext.*
//
//fun Application.configureUserRoutes() {
//    val userRepository by inject<UserRepository>()
////    val authService by inject<AuthService>()
//    routing {
//        route("${ApiRestVersioning.V1}/${ApiRestResources.USERS}") {
////            createUser(userRepository)
////            deleteUser(userRepository)
////            findUser(userRepository)
////            updateUser(userRepository)
////            getUserList(userRepository)
//            route("/login") {
////            login(userRepository, authService)
//            }
//            authenticate("auth-jwt") {
//                get("/hello") {
//                    val principal = call.principal<JWTPrincipal>()
//                    val firstname = principal!!.payload.getClaim("firstName").asString()
//                    val expiresAt = principal.expiresAt?.time.let {
//                        val currentTimeMillis = System.currentTimeMillis()
//                        val differenceInMillis = it?.minus(currentTimeMillis)
//                        val differenceInDays = differenceInMillis?.div((24 * 60 * 60 * 1000))
//                        differenceInDays
//                    }
//                    call.respondText("Hello, $firstname! El token expira en $expiresAt days.")
//                }
//            }
//        }
//    }
//}
////fun Route.createUser(userRepository: UserRepository) {
////    post {
////        try {
////            val entity = call.receive<AddUserDTO>()
////            val success =
////                userRepository.createUser(
////                    firstname = entity.firstName,
////                    lastname = entity.lastName,
////                    email = entity.email,
////                    password = entity.password
////                )
////
////            success.fold(
////                ifLeft = {
//////                    call.respond(HttpStatusCode.BadRequest, ErrorResponse(it.errorMessage))
////                    call.respond(HttpStatusCode.BadRequest, ErrorResponse("error message"))
////                },
////                ifRight = {
////                    call.respond(HttpStatusCode.Created)
////                },
////            )
////        } catch (e: Exception) {
////            call.respond(HttpStatusCode.BadRequest, ErrorResponse(e.message ?: "unexpected"))
////        }
////    }
////}
////
////fun Route.deleteUser(userRepository: UserRepository) {
////    delete("/{id}") {
////        try {
////            val id: Int = call.parameters["id"]?.toIntOrNull()!!
////            val success = userRepository.deleteUser(id)
////            success.fold(
////                ifLeft = {
////                    call.respond(
////                        HttpStatusCode.BadRequest,
////                        ErrorResponse("Cannot delete user with id [$id]"),
////                    )
////                },
////                ifRight = {
////                    call.respond(HttpStatusCode.NoContent, message = "User with id [$id] deleted!!")
////                },
////            )
////        } catch (e: Exception) {
////            call.respond(HttpStatusCode.BadRequest, ErrorResponse(e.message ?: "unexpected"))
////        }
////    }
////}
////
////fun Route.updateUser(userRepository: UserRepository) {
////    patch("/{id}") {
////        try {
////            val entity = call.receive<UpdateUserDTO>()
////            val id: Int = call.parameters["id"]?.toIntOrNull()!!
////            val success =
////                userRepository.updateUser(
////                    User(
////                        id = id,
////                        firstName = entity.firstName,
////                        lastName = entity.lastName,
////                        email = entity.email,
////                        password = entity.password
////                    ),
////                )
////            success.fold(
////                ifLeft = {
////                    call.respond(
////                        HttpStatusCode.BadRequest,
////                        call.respond(
////                            HttpStatusCode.BadRequest,
////                            ErrorResponse("Cannot update user with id [$id]"),
////                        ),
////                    )
////                },
////                ifRight = {
////                    call.respond(it.toDetailUserDTO())
////                },
////            )
////        } catch (e: Exception) {
////            call.respond(HttpStatusCode.BadRequest, ErrorResponse(e.message ?: "unexpected"))
////        }
////    }
////}
////
////fun Route.findUser(userRepository: UserRepository) {
////    get("/{id}") {
////        try {
////            val id: Int = call.parameters["id"]?.toIntOrNull()!!
////            val user = userRepository.findById(id)
////            user.fold(
////                ifLeft = {
////                    call.respond(
////                        HttpStatusCode.BadRequest,
////                        call.respond(
////                            HttpStatusCode.BadRequest,
////                            ErrorResponse("Cannot find user with id [$id]"),
////                        ),
////                    )
////                },
////                ifRight = {
////                    call.respond(it.toDetailUserDTO())
////                },
////            )
////        } catch (e: Exception) {
////            call.respond(HttpStatusCode.BadRequest, ErrorResponse(e.message ?: "unexpected"))
////        }
////    }
////}
////
////fun Route.getUserList(userRepository: UserRepository) {
////    get {
////        val users =
////            userRepository.getUserList()
////
////        users.fold(
////            ifLeft = {
////                call.respond(
////                    HttpStatusCode.BadRequest,
////                    call.respond(
////                        HttpStatusCode.BadRequest,
////                        ErrorResponse("Cannot find users"),
////                    ),
////                )
////            },
////            ifRight = {
////                call.respond(it.map { user -> user.toDetailUserDTO() })
////            },
////        )
////        call.respond(message = users)
////    }
////}
////
////fun Route.login(userRepository: UserRepository, authService: AuthService) {
////    post {
////        try {
////            val credentials = call.receive<LoginUserDTO>()
//////            val authResponse = authService.authenticate()
////            val auth = userRepository.authUser(
////                firstName = credentials.firstName,
////                password = credentials.password
////            )
////            auth.fold(
////                ifLeft = {
////                    call.respond(
////                        HttpStatusCode.BadRequest,
////                        call.respond(
////                            HttpStatusCode.BadRequest,
////                            ErrorResponse("Cannot login"),
////                        ),
////                    )
////                },
////                ifRight = {
////                    it.let {
////                        val respondData = JwtAuthDTO(it.accessToken, it.refreshToken)
////                        call.respond(hashMapOf("response" to respondData))
////                    }
//////                    call.respond(hashMapOf("token" to auth))
////                },
////            )
////        } catch (e: Exception) {
////            call.respond(HttpStatusCode.BadRequest, ErrorResponse(e.message ?: "unexpected"))
////        }
////    }
////
////    post("/refresh") {
////        val request = call.receive<RefreshTokenDTO>()
////        val newAccessToken = userRepository.refreshToken(token = request.token)
////
////        newAccessToken.fold(
////            ifLeft = {
////                call.respond(HttpStatusCode.BadRequest, ErrorResponse(it.errorMessage))
////            },
////            ifRight = {
////                call.respond(
////                    it.toRefreshDTO()
////                )
////            }
////        )
////    }
////}
////
////
