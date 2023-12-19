package dev.pango.modules.userauth.data.dto.user

import dev.pango.modules.userauth.domain.entity.CreateUserEntity
import dev.pango.modules.userauth.domain.entity.UpdateUserEntity
import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserDTO(
    val firstName: String,

)

fun UpdateUserDTO.toUserDomain() = UpdateUserEntity(firstName)