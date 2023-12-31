package dev.pango.apollo.backend.modules.userauth.mapper

import dev.pango.apollo.backend.modules.userauth.domain.entity.*
import dev.pango.apollo.backend.modules.userauth.presentation.user.apirest.dto.*

fun RegisterUserDTO.toUser() = User(
    userName = username,
    firstName = firstname,
    lastName = lastname,
    email = email,
    password = password
)