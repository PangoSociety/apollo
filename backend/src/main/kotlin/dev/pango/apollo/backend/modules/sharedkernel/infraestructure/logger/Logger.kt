package dev.pango.apollo.backend.modules.sharedkernel.infraestructure.logger

import org.slf4j.*

inline fun <reified T> T.getLogger(): Logger {
    return LoggerFactory.getLogger(T::class.java)
}
