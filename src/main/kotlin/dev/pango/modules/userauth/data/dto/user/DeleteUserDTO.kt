package dev.pango.modules.userauth.data.dto.user

import dev.pango.modules.userauth.domain.entity.DeleteUserEntity
import kotlinx.serialization.Serializable


@Serializable
data class DeleteUserDTO(
    val id: Int,
)

fun DeleteUserDTO.toUserDomain() = DeleteUserEntity(id)