package dev.pango.apollo.backend.modules.userauth.presentation.user.apirest.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginUserDTO(
    @SerialName("email")
    val email: String,
    @SerialName("password")
    val password: String,
)
