package dev.pango.apollo.backend.modules.userauth.presentation.user.apirest.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class AuthTokenDTO(
    @SerialName("accessToken")
    val token: String,
)
