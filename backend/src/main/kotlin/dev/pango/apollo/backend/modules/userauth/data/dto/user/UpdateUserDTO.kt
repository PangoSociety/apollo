package dev.pango.apollo.backend.modules.userauth.data.dto.user

import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserDTO(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
)
