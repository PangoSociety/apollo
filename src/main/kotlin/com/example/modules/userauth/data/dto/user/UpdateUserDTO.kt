package com.example.modules.userauth.data.dto.user

import com.example.modules.userauth.domain.entity.CreateUserEntity
import com.example.modules.userauth.domain.entity.UpdateUserEntity
import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserDTO(
    val firstName: String,

)

fun UpdateUserDTO.toUserDomain() = UpdateUserEntity(firstName)