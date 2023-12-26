package dev.pango.apollo.backend.plugins

import dev.pango.apollo.backend.framework.http.*
import dev.pango.apollo.backend.modules.sharedkernel.infraestructure.config.*
import dev.pango.apollo.backend.modules.sharedkernel.presentation.apirest.routes.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.*

fun Application.configureRouting() {
    val apolloConfig by inject<ApolloConfig>()
    val callFailedPlugin = buildCallFailedPlugin(apolloConfig)
    install(callFailedPlugin)
    routing {
        restApiRoutes()
    }
}
