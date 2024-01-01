package dev.pango.apollo.backend.framework.crypto

import org.mindrot.jbcrypt.BCrypt

class CryptoService {
    fun hashPassword(password: String) =
        BCrypt.hashpw(password, BCrypt.gensalt())

    fun checkPassword(
        plainPassword: String,
        hashedPassword: String,
    ): Boolean {
        return BCrypt.checkpw(plainPassword, hashedPassword)
    }
}
