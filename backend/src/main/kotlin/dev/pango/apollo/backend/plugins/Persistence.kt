package dev.pango.apollo.backend.plugins

import dev.pango.apollo.backend.modules.sharedkernel.infraestructure.persistence.*
import io.ktor.server.application.*

fun Application.configurePersistence() {
    initDatabase()
}
