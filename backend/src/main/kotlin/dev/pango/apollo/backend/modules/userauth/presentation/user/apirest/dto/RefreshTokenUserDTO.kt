package dev.pango.apollo.backend.modules.userauth.presentation.user.apirest.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokenUserDTO(
    @SerialName("id")
    val id: String,
    @SerialName("email")
    val email: String,
)