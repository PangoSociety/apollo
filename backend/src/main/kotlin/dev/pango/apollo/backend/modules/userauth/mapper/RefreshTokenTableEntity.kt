package dev.pango.apollo.backend.modules.userauth.mapper

import dev.pango.apollo.backend.modules.userauth.domain.entity.RefreshToken
import dev.pango.apollo.backend.modules.userauth.infraestructure.persistence.entity.RefreshTokenTableEntity

fun RefreshTokenTableEntity.toRefreshToken() =
    RefreshToken(
        id = id.value,
        email = email,
    )
