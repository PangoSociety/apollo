package dev.pango.apollo.backend.modules.sharedkernel.infraestructure.config

enum class Environment(val rawValue: String) {
    DEVELOPMENT("development"),
    PRODUCTION("production"),
    STAGING("staging"),
}
