package dev.pango.apollo.backend.modules.userauth.mapper

import dev.pango.apollo.backend.modules.userauth.domain.entity.*
import dev.pango.apollo.backend.modules.userauth.presentation.user.apirest.dto.*

fun User.toUserDTO() = UserDTO(
    id = id,
    username = userName,
    firstname = firstName,
    lastname = lastName,
    email = email,
    password = password

)

fun List<User>.toUserListDTO() = map {
    it.toUserDTO()
}