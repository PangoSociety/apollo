package dev.pango.apollo.backend.modules.userauth.domain.repository

import arrow.core.Either
import dev.pango.apollo.backend.modules.sharedkernel.domain.failure.Failure

interface RefreshTokenRepository {

    suspend fun findUsernameByToken(token: String): String?

    suspend fun save(token: String, username: String)
}