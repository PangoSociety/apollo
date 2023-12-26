package dev.pango.apollo.backend.modules.sharedkernel.presentation.apirest.routes

import dev.pango.apollo.backend.modules.sharedkernel.presentation.apirest.config.*
import io.ktor.server.routing.*

fun Routing.restApiRoutes() {
    route(ApiRestConstants.Suffix.API) {
        apiRoutesV1()
    }
}
