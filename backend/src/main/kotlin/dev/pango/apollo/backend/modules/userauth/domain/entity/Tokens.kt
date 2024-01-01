package dev.pango.apollo.backend.modules.userauth.domain.entity

data class Tokens(
    val accessToken: String,
    val refreshToken: String,
)
