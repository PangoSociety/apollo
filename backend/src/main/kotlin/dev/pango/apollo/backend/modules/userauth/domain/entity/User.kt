package dev.pango.apollo.backend.modules.userauth.domain.entity

data class User(
    val id: Int,
    var firstName: String,
    var lastName: String,
    var email: String,
    var password: String,
)
