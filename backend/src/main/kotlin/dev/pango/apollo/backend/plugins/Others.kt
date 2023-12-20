package dev.pango.apollo.backend.plugins

import java.security.MessageDigest
import java.util.UUID

fun generateFileName(): UUID {
    val randomizeId = UUID.randomUUID()
    return randomizeId
}

fun hashFileName(fileName: String): String {
    val bytes = fileName.toByteArray()
    val digest = MessageDigest.getInstance("SHA-256").digest(bytes)
    return digest.joinToString("") { "%02x".format(it) }
}

fun extractFileExtension(fileName: String): String {
    val lastDotIndex = fileName.lastIndexOf('.')
    return if (lastDotIndex != -1 && lastDotIndex < fileName.length - 1) {
        fileName.substring(lastDotIndex + 1)
    } else {
        ""
    }
}
