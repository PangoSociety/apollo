package dev.pango.apollo.backend.modules.userauth.presentation.user.apirest.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.*

@Serializable
data class RegisterUserDTO(
    @SerialName("userName")
    val username: String,
    @SerialName("firstName")
    val firstname: String,
    @SerialName("lastName")
    val lastname: String,
    @SerialName("email")
    val email: String,
    @SerialName("password")
    val password: String,
)