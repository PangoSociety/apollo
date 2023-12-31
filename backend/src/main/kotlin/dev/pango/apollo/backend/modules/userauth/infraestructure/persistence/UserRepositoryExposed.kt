package dev.pango.apollo.backend.modules.userauth.infraestructure.persistence

import arrow.core.*
import dev.pango.apollo.backend.framework.functional.*
import dev.pango.apollo.backend.modules.sharedkernel.domain.repository.*
import dev.pango.apollo.backend.modules.userauth.domain.entity.*
import dev.pango.apollo.backend.modules.userauth.domain.repository.*
import dev.pango.apollo.backend.modules.userauth.infraestructure.persistence.entity.*
import dev.pango.apollo.backend.modules.userauth.mapper.*
import org.jetbrains.exposed.dao.id.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.transactions.*
import org.mindrot.jbcrypt.*
import java.util.*

/**
 * Repositorio no puede depender de un servicio
 * Repositorio unicamente guarda
 *
 *
 */
class UserRepositoryExposed(
) : UserRepository {

    override fun getUserById(id: UUID): Either<RepositoryFailure, User> =
        Either.catch {
            transaction {
                UserTableEntity[id].toUser()
            }
        }.mapLeftLogged {
            RepositoryFailure.DataSourceAccessException(it)
        }

    override fun getUserList(): Either<RepositoryFailure, List<User>> =
        Either.catch {
            transaction {
                UserTableEntity.all().map { it.toUser() }
            }
        }.mapLeftLogged {
            RepositoryFailure.DataSourceAccessException(it)
        }

    override fun getUserByEmail(
        email: String,
    ): Either<RepositoryFailure, User> =
        Either.catch {
            transaction {
                val user = UserTableEntity.find { UserTable.email eq email }.singleOrNull()!!
                user.toUser()
            }
        }.mapLeftLogged {
            RepositoryFailure.DataSourceAccessException(it)
        }

    // Cambiale el nombre a loginUser en application servic
    //
//    override fun authUser(
//        userName: String,
//        password: String,
//    ): Either<RepositoryFailure, Tokens> =
//        Either.catch {
//
//            transaction {
//                val user = UserTableEntity.find { UserTable.userName eq userName }.singleOrNull()!!
//                if (checkPassword(password, user.password)) {
//                    // hasta 68 debe ir en un servicio
//                    /**
//                     * AuthApplicationService {
//                     *   authUser() {
//                     *    val accessToken = jwtApplicationService.createAccessToken(user.firstname)
//                     *                     val refreshToken = jwtApplicationService.createRefreshToken(user.firstname)
//                     *                     val refreshTokenUUID = UUID.fromString(refreshToken)
//                     *                     authRepository.saveRefreshToken(refreshtoken)
//                     *   }
//                     * }
//                     */
//
//
//                    Tokens(accessToken, refreshToken)
//                } else {
//                    Tokens("a", "b")
//                }
//            }
//        }.mapLeftLogged {
//            RepositoryFailure.DataSourceAccessException(it)
//        }
    override fun refreshToken(token: String): Either<RepositoryFailure, RefreshTokenResponse> {
        TODO("Not yet implemented")
    }

    override fun updateUser(
        user: User,
    ): Either<RepositoryFailure, User> =
        Either.catch {
            val updateUser = transaction {
                //TODO refactor el nonnull
                val userEntity = UserTableEntity.findById(EntityID(user.id, UserTable))!!
                userEntity.username = user.userName
                userEntity.firstname = user.firstName
                userEntity.lastname = user.lastName
                userEntity.email = user.email
                userEntity.password = hashPassword(user.password)

                userEntity
            }
            updateUser.toUser()
        }.mapLeftLogged {
            RepositoryFailure.DataSourceAccessException(it)
        }

    override fun deleteUser(id: UUID): Either<RepositoryFailure, Unit> {
        //TODO implement successful delete response
        return Either.catch {
            transaction {
                //TODO cambiar UserTable por UserTableEntity
                UserTable.deleteWhere { UserTable.id eq id }
            }
        }.mapLeftLogged {
            RepositoryFailure.DataSourceAccessException(it)
        }.map { Unit }
    }

    override fun createUser(
        user: User,
    ): Either<RepositoryFailure, User> =
        Either.catch {
            val newUser =
                transaction {
                    UserTableEntity.new(user.id) {
                        username = user.userName
                        firstname = user.firstName
                        lastname = user.lastName
                        email = user.email
                        password = user.password
                    }
                }
            newUser.toUser()
        }.mapLeftLogged {
            RepositoryFailure.DataSourceAccessException(it)
        }

    private fun hashPassword(password: String) =
        BCrypt.hashpw(password, BCrypt.gensalt())

    private fun checkPassword(plainPassword: String, hashedPassword: String): Boolean {
        return BCrypt.checkpw(plainPassword, hashedPassword)
    }
}
