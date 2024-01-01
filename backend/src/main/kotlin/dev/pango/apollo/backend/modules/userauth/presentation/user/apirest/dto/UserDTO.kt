package dev.pango.apollo.backend.modules.userauth.presentation.user.apirest.dto

import dev.pango.apollo.backend.framework.serialization.*
import kotlinx.serialization.*
import org.jetbrains.exposed.dao.id.*
import java.util.UUID

@Serializable
data class UserDTO(
    @SerialName("id")
    @Serializable(UUIDSerializer::class)
    val id: UUID,
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
