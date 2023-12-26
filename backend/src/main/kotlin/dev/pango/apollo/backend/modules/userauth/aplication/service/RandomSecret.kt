package dev.pango.apollo.backend.modules.userauth.aplication.service

import java.security.SecureRandom
import java.util.Base64

fun generateRandomSecret(length: Int): String {
    val random = SecureRandom()
    val bytes = ByteArray(length)
    random.nextBytes(bytes)
    return Base64.getEncoder().encodeToString(bytes)
}


