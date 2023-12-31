package dev.pango.apollo.backend.modules.userauth.mapper

import dev.pango.apollo.backend.modules.userauth.domain.entity.*
import dev.pango.apollo.backend.modules.userauth.presentation.user.apirest.dto.*

fun Tokens.toAuthTokenDTO() = TokensDTO(
    accessToken = accessToken,
    refreshToken = refreshToken
)

fun RefreshToken.toRefreshTokenDTO() = RefreshTokenUserDTO(
    id = id,
    email = email
)

fun AuthToken.toAuthTokenDTO() = AuthTokenDTO(
    token = token
)