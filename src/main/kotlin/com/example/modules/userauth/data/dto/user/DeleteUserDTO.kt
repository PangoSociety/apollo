package com.example.modules.userauth.data.dto.user

import com.example.modules.userauth.domain.entity.DeleteUserEntity
import kotlinx.serialization.Serializable


@Serializable
data class DeleteUserDTO(
    val id: Int,
)

fun DeleteUserDTO.toUserDomain() = DeleteUserEntity(id)