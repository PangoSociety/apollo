package dev.pango.apollo.backend.modules.userauth.presentation.user.apirest.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokenDTO(
    @SerialName("refreshToken")
    val token: String,
)
