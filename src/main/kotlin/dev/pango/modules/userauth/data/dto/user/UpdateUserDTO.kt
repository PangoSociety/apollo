package dev.pango.modules.userauth.data.dto.user

import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserDTO(
    val firstName: String,
)

