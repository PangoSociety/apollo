package dev.pango.apollo.backend

import dev.pango.apollo.backend.plugins.*
import dev.pango.apollo.backend.routes.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, module = Application::module).start(wait = true)
}

fun Application.module() {
    configureDependencyInjection() // This should be the first one
    configurePersistence()
    configureSerialization()
    configureAuth()
//    configureUserRoutes()
    configureFileRoutes()
    configureRouting()
}
