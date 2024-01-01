package dev.pango.apollo.backend.framework.http

import de.comahe.i18n4k.*
import dev.pango.apollo.backend.modules.sharedkernel.infraestructure.logger.*
import io.ktor.http.*
import io.ktor.server.request.*

fun HeaderValue.toLocale() = Locale(this.value)

fun List<HeaderValue>.getDefaultLocale(): Locale =
    try {
        first().toLocale()
    } catch (e: Exception) {
        // getLogger().warn("Failed to get default locale", e)
        Locale.ENGLISH
    }

fun ApplicationRequest.getLocale() = this.acceptLanguageItems().getDefaultLocale()
