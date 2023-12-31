package dev.pango.apollo.backend.modules.userauth.domain.entity

import java.util.UUID

data class RefreshToken(
    val id: String,
    val email: String
)
