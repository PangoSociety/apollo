package dev.pango.apollo.backend.modules.userauth.data.dto.user

import kotlinx.serialization.Serializable

@Serializable
data class DetailUserDTO(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String
)