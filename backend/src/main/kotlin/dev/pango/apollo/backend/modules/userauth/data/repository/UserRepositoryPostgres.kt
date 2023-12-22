package dev.pango.apollo.backend.modules.userauth.data.repository

import arrow.core.*
import com.auth0.jwt.interfaces.DecodedJWT
import dev.pango.apollo.backend.modules.sharedkernel.domain.failure.*
import dev.pango.apollo.backend.modules.sharedkernel.infraestructure.persistence.*
import dev.pango.apollo.backend.modules.userauth.aplication.config.JwtConfig
import dev.pango.apollo.backend.modules.userauth.aplication.service.AuthService
import dev.pango.apollo.backend.modules.userauth.domain.entity.AuthResponse
import dev.pango.apollo.backend.modules.userauth.domain.entity.RefreshTokenResponse
import dev.pango.apollo.backend.modules.userauth.domain.entity.User
import dev.pango.apollo.backend.modules.userauth.domain.repository.*
import dev.pango.apollo.backend.modules.userauth.infraestructure.persistence.tables.*
import dev.pango.apollo.backend.modules.userauth.mapper.toUserDomain
import kotlinx.serialization.*
import org.ktorm.dsl.*
import org.ktorm.entity.*
import org.mindrot.jbcrypt.BCrypt

class UserRepositoryPostgres(
    private val authService: AuthService,
    private val refreshTokenRepository: RefreshTokenRepository,
) : UserRepository {

    override suspend fun findById(id: Int): Either<Failure, User> {
        return try {
            val data = getUserFromDb(id)
            if (data is UserKtorm) {
                Either.Right(data.toUserDomain())
            } else {
                Either.Left(Failure.DatabaseError)
            }
        } catch (e: Exception) {
            Either.Left(Failure.Generic(e))
        }
    }

    override suspend fun findAll(): Either<Failure, List<User>> {
        return try {
            val data =
                database.sequenceOf(UserTable).toList()
            if (data.isNotEmpty()) {
                Either.Right(data.map { it.toUserDomain() })
            } else {
                Either.Left(Failure.DatabaseError)
            }
        } catch (e: Exception) {
            Either.Left(Failure.Generic(e))
        }
    }

    override suspend fun getUserByCredentials(
        firstName: String,
        lastname: String,
    ): Either<Failure, User> {
        return try {
            val data = database.sequenceOf(UserTable)
                .find { user -> user.firstName eq firstName and (user.lastName eq lastname) }
            if (data is UserKtorm) {
                Either.Right(data.toUserDomain())
            } else {
                Either.Left(Failure.DatabaseError)
            }
        } catch (e: Exception) {
            Either.Left(Failure.Generic(e))
        }
    }

    override suspend fun updateUser(
        user: User,
    ): Either<Failure, User> {
        return try {
            val foundUser = getUserFromDb(user.id)
            if (foundUser != null) {
                foundUser.firstName = user.firstName
                foundUser.lastName = user.lastName
                foundUser.email = user.email
                val affectedRecordsNumber = foundUser.flushChanges()
                if (affectedRecordsNumber == 1) {
                    Either.Right(foundUser.toUserDomain())
                } else {
                    Either.Left(Failure.DatabaseError)
                }
            } else {
                Either.Left(Failure.DatabaseError)
            }
        } catch (e: Exception) {
            Either.Left(Failure.Generic(e))
        }
    }

    override suspend fun deleteUser(id: Int): Either<Failure, Unit> {
        return try {
            val foundUser = getUserFromDb(id)
            if (foundUser is UserKtorm) {
                val affectedRecordsNumber = foundUser.delete()
                if (affectedRecordsNumber == 1) {
                    Either.Right(Unit)
                } else {
                    Either.Left(Failure.DatabaseError)
                }
            } else {
                Either.Left(Failure.DatabaseError)
            }
        } catch (e: Exception) {
            Either.Left(Failure.Generic(e))
        }
    }

    override suspend fun createUser(
        firstname: String,
        lastname: String,
        email: String,
        password: String
    ): Either<Failure, User> {
        try {
            val newUser =
                UserKtorm {
                    this.firstName = firstname
                    this.lastName = lastname
                    this.email = email
                    this.password =hashPassword(password)
                }
            val affectedRecordsNumber =
                database.sequenceOf(UserTable)
                    .add(newUser)

            return if (affectedRecordsNumber == 1) {
                Either.Right(newUser.toUserDomain())
            } else {
                Either.Left(Failure.DatabaseError)
            }
        } catch (e: Exception) {
            return Either.Left(Failure.Generic(e))
        }
    }

    override suspend fun authUser(
        firstName: String,
        password: String,
    ): Either<Failure, AuthResponse> {
        return try {
            val foundUser = database.sequenceOf(UserTable)
                .find { user -> user.firstName eq firstName }
            if (foundUser is UserKtorm) {
                if( checkPassword(password, foundUser.password)) {
                    val accessToken = authService.createAccessToken(firstName)
                    val refreshToken = authService.createRefreshToken(firstName)
                    refreshTokenRepository.save(refreshToken, firstName)
                    Either.Right(
                        AuthResponse(
                            accessToken, refreshToken
                        )
                    )
                } else {
                    Either.Left(Failure.DatabaseError)
                }

            } else {
                Either.Left(Failure.DatabaseError)
            }
        } catch (e: Exception) {
            Either.Left(Failure.Generic(e))
        }
    }

    override suspend fun refreshToken(token: String): Either<Failure, RefreshTokenResponse> {
        return try {
            val decodedRefreshToken = verifyRefreshToken(token)
            val persistedUsername = refreshTokenRepository.findUsernameByToken(token)
            if (decodedRefreshToken != null && persistedUsername != null) {
                val foundUser: UserKtorm? = findByFirstName(persistedUsername)
                val usernameFromRefreshToken: String? =
                    decodedRefreshToken.getClaim("firstName").asString()

                if (foundUser != null && usernameFromRefreshToken == foundUser.firstName)
                    Either.Right(
                        RefreshTokenResponse(
                            authService.createAccessToken(
                                persistedUsername
                            )
                        )
                    )
                else {
                    Either.Left(Failure.Generic(Throwable("11")))
                }
            } else {

                Either.Left(Failure.Generic(Throwable("1122")))
            }
        } catch (e: Exception) {
            Either.Left(Failure.Generic(e))
        }
    }

    private fun findByFirstName(firstName: String): UserKtorm? {
        return database.sequenceOf(UserTable)
            .find { user -> user.firstName eq firstName }
    }

    private fun getUserFromDb(id: Int): UserKtorm? {
        return database.sequenceOf(UserTable)
            .find { user -> user.id eq id }
    }

    private fun verifyRefreshToken(token: String): DecodedJWT? {
        val decodedJwt: DecodedJWT? = getDecodedJwt(token)

        return decodedJwt?.let {
            decodedJwt
        }
    }

    private fun getDecodedJwt(token: String): DecodedJWT? =
        try {
            JwtConfig.verifier.verify(token)
        } catch (ex: Exception) {
            null
        }

    private fun hashPassword(password:String) =
        BCrypt.hashpw(password, BCrypt.gensalt())

    private fun checkPassword(plainPassword: String, hashedPassword: String): Boolean {
        return BCrypt.checkpw(plainPassword, hashedPassword)
    }
}

@Serializable
data class ErrorResponse(val message: String)
