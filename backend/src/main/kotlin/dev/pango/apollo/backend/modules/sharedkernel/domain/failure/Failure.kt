package dev.pango.apollo.backend.modules.sharedkernel.domain.failure

sealed class Failure(val errorMessage: String) {
    data class Generic(val throwable: Throwable) : Failure(throwable.message!!)

    object UserNotFound : Failure("User")

    object DatabaseError : Failure("Database error")
}
