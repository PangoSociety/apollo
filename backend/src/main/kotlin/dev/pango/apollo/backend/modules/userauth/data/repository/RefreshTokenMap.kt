package dev.pango.apollo.backend.modules.userauth.data.repository

import dev.pango.apollo.backend.modules.userauth.domain.repository.RefreshTokenRepository

class RefreshTokenMap : RefreshTokenRepository {
    private val tokens = mutableMapOf<String, String>()

    override suspend fun findUsernameByToken(token: String): String? =
        tokens[token]
    override suspend fun save(token: String, username: String) {
        tokens[token] = username
    }
}