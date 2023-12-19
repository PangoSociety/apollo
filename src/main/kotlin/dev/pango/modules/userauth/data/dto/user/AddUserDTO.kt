package dev.pango.modules.userauth.data.dto.user

import dev.pango.models.Users.primaryKey
import dev.pango.modules.userauth.domain.entity.CreateUserEntity
import kotlinx.serialization.Serializable
import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

@Serializable
data class AddUserDTO(
    val firstName: String,
    val lastName: String,
    val email: String,
)

fun AddUserDTO.toUserDomain() = CreateUserEntity(firstName, lastName, email)