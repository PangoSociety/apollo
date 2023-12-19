package com.example.modules.sharedkernel.domain.failure

import org.jetbrains.exposed.sql.Op

sealed class Failure(val errorMessage: String) {
    data class Generic(val throwable: Throwable): Failure(throwable.message!!)
    object UserNotFound: Failure("User")
    object DatabaseError: Failure("Database error")
}