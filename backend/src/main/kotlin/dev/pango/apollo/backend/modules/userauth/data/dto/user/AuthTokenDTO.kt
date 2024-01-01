package dev.pango.apollo.backend.modules.userauth.data.dto.user

import kotlinx.serialization.Serializable

@Serializable
data class AuthTokenDTO(
    val token: String,
)
