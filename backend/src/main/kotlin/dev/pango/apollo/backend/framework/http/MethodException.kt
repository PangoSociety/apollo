package dev.pango.apollo.backend.framework.http

import SharedKernel
import dev.pango.apollo.backend.modules.sharedkernel.infraestructure.config.*
import dev.pango.apollo.backend.modules.sharedkernel.infraestructure.logger.*
import dev.pango.apollo.backend.modules.sharedkernel.mapper.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.application.hooks.*
import io.ktor.server.plugins.*
import io.ktor.server.response.*
import io.ktor.util.logging.*

fun buildCallFailedPlugin(apolloConfig: ApolloConfig): ApplicationPlugin<Unit> =
    createApplicationPlugin(name = "CallFailedPlugin") {
        on(CallFailed) { call, cause ->
            getLogger().error(cause)
            val locale = call.request.getLocale()
            val genericMessage = if (apolloConfig.isProduction()) SharedKernel.failureGeneric(locale).toFailureDTO() else cause.localizedMessage.toFailureDTO()
            when (cause) {
                is BadRequestException -> call.respond(HttpStatusCode.BadRequest, SharedKernel.failureBadRequest(locale).toFailureDTO())
                else -> call.respond(HttpStatusCode.BadRequest, genericMessage)
            }
        }
    }
