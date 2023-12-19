package dev.pango

import dev.pango.dao.*
import dev.pango.modules.userauth.presentation.apirest.configureUserRoutes
import dev.pango.plugins.*
import dev.pango.routes.configureFileRoutes
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureSerialization()
        configureAuth()
        configureUserRoutes()
        configureFileRoutes()
    }.start(wait = true)

}