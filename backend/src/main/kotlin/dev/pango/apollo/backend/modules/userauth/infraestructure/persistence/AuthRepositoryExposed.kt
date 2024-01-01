package dev.pango.apollo.backend.modules.userauth.infraestructure.persistence

import arrow.core.Either
import dev.pango.apollo.backend.framework.functional.mapLeftLogged
import dev.pango.apollo.backend.modules.sharedkernel.domain.repository.RepositoryFailure
import dev.pango.apollo.backend.modules.userauth.domain.entity.RefreshToken
import dev.pango.apollo.backend.modules.userauth.domain.repository.AuthRepository
import dev.pango.apollo.backend.modules.userauth.infraestructure.persistence.entity.RefreshTokenTableEntity
import dev.pango.apollo.backend.modules.userauth.mapper.toRefreshToken
import org.jetbrains.exposed.sql.transactions.transaction

class AuthRepositoryExposed() : AuthRepository {
    override fun findEmailByToken(token: String): Either<RepositoryFailure, String> =
        Either.catch {
            transaction {
                RefreshTokenTableEntity[token].email
            }
        }.mapLeftLogged {
            RepositoryFailure.DataSourceAccessException(it)
        }

    override fun saveRefreshToken(
        refreshToken: String,
        email: String,
    ): Either<RepositoryFailure, RefreshToken> =
        Either.catch {
            val newRefreshToken =
                transaction {
                    RefreshTokenTableEntity.new(refreshToken) {
                        this.email = email
                    }
                }
            newRefreshToken.toRefreshToken()
        }.mapLeftLogged {
            RepositoryFailure.DataSourceAccessException(it)
        }
}
