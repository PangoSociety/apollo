package dev.pango.apollo.backend.modules.userauth.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class UserEntity(val id: Int, val userName: String, val lastName: String, val email: String)
