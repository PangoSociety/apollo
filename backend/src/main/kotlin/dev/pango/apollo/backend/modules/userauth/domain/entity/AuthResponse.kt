package dev.pango.apollo.backend.modules.userauth.domain.entity

data class AuthResponse(
    val accessToken: String,
    val refreshToken: String,
)