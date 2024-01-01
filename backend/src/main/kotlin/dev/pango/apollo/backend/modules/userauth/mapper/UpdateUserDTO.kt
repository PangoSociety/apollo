package dev.pango.apollo.backend.modules.userauth.mapper

import dev.pango.apollo.backend.modules.userauth.domain.entity.User
import dev.pango.apollo.backend.modules.userauth.presentation.user.apirest.dto.UpdateUserDTO

fun UpdateUserDTO.toUser() =
    User(
        userName = username,
        firstName = firstname,
        lastName = lastname,
        email = email,
        password = password,
    )
