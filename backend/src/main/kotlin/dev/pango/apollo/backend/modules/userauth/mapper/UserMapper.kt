package dev.pango.apollo.backend.modules.userauth.mapper

import dev.pango.apollo.backend.modules.userauth.data.dto.user.DetailUserDTO
import dev.pango.apollo.backend.modules.userauth.data.dto.user.UpdateUserDTO
import dev.pango.apollo.backend.modules.userauth.domain.entity.User

// fun UserKtorm.toUserDomain() =
//    User(
//        firstName = firstName,
//        email = email,
//        lastName = lastName,
//        id = id,
//    )

fun User.toDetailUserDTO() =
    DetailUserDTO(
        firstName = firstName,
        email = email,
        lastName = lastName,
        id = id,
    )

fun User.toUpdateUserDTO() =
    UpdateUserDTO(
        firstName = firstName,
        email = email,
        lastName = lastName,
    )
