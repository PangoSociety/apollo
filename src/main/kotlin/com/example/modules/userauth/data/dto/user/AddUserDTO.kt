package com.example.modules.userauth.data.dto.user

import com.example.models.Users.primaryKey
import com.example.modules.userauth.domain.entity.CreateUserEntity
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