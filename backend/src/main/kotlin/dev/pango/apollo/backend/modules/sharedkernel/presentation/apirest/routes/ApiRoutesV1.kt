package dev.pango.apollo.backend.modules.sharedkernel.presentation.apirest.routes

import dev.pango.apollo.backend.modules.educational.presentation.course.apirest.routes.*
import dev.pango.apollo.backend.modules.sharedkernel.presentation.apirest.config.*
import io.ktor.server.routing.*

fun Route.apiRoutesV1() {
    route(ApiRestConstants.Versions.V1) {
        courseRoutesV1()
    }
}
