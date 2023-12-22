package dev.pango.apollo.backend

import dev.pango.apollo.backend.modules.userauth.mapper.configureAuth
import dev.pango.apollo.backend.modules.userauth.presentation.apirest.configureUserRoutes
import dev.pango.apollo.backend.plugins.configureSerialization
import dev.pango.apollo.backend.routes.configureFileRoutes
import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module).start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureAuth()
    configureUserRoutes()
    configureFileRoutes()
}
