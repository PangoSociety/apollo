package dev.pango.apollo.backend.modules.userauth.domain.entity

import java.util.*

data class User(
    val id: UUID = UUID.randomUUID(),
    var userName: String,
    var firstName: String,
    var lastName: String,
    var email: String,
    var password: String,
)
