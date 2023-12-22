package dev.pango.apollo.backend.plugins

import dev.pango.apollo.backend.modules.sharedkernel.presentation.apirest.routes.*
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        restApiRoutes()
    }
}
