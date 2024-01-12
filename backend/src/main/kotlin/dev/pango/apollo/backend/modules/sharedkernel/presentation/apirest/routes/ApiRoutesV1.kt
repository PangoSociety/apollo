package dev.pango.apollo.backend.modules.sharedkernel.presentation.apirest.routes

import dev.pango.apollo.backend.modules.educational.presentation.course.apirest.routes.*
import dev.pango.apollo.backend.modules.sharedkernel.presentation.apirest.config.*
import dev.pango.apollo.backend.modules.subscription.presentation.subscription.apirest.routes.subscriptionRoutesV1
import io.ktor.server.routing.*

fun Route.apiRoutesV1() {
    route(ApiRestConstants.Versions.V1) {
        courseRoutesV1()
        subscriptionRoutesV1()
    }
}
