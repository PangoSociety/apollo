package dev.pango.apollo.backend.modules.userauth.mapper

import dev.pango.apollo.backend.modules.userauth.domain.entity.User
import dev.pango.apollo.backend.modules.userauth.infraestructure.persistence.entity.UserTableEntity

fun UserTableEntity.toUser() =
    User(
        id = id.value,
        userName = username,
        firstName = firstname,
        lastName = lastname,
        email = email,
        password = password,
    )
// fun User.toDetailUserDTO() =
//    DetailUserDTO(
//        id = id,
//        firstName = firstName,
//        email = email,
//        lastName = lastName,
//    )
//
// fun User.toUpdateUserDTO() =
//    UpdateUserDTO(
//        firstName = firstName,
//        email = email,
//        lastName = lastName,
//        password = password
//    )
