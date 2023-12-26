package dev.pango.apollo.backend.modules.userauth.infraestructure.persistence.tables

// interface User : Entity<User> {
interface User {
//    companion object : Entity.Factory<User>()
    val id: Int?
    var firstName: String
    var lastName: String
    var email: String
}

// object UserTable : Table<User>("users") {
object UserTable {
//    val id = int("id").primaryKey().bindTo(User::id)
//    val firstName = varchar("firstname").bindTo(User::firstName)
//    val lastName = varchar("lastname").bindTo(User::lastName)
//    val email = varchar("email").bindTo(User::email)
}
