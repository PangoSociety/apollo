package dev.pango.apollo.backend.modules.userauth.domain.entity

import kotlinx.serialization.Serializable

data class RefreshTokenResponse (
    val token: String,

)