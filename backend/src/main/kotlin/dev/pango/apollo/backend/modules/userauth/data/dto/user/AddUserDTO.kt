package dev.pango.apollo.backend.modules.userauth.data.dto.user

import kotlinx.serialization.Serializable

@Serializable
data class AddUserDTO(
    val firstName: String,
    val lastName: String,
    val email: String,
)
