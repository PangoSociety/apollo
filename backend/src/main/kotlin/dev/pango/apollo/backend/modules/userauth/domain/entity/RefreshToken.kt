package dev.pango.apollo.backend.modules.userauth.domain.entity

data class RefreshToken(
    val id: String,
    val email: String,
)
