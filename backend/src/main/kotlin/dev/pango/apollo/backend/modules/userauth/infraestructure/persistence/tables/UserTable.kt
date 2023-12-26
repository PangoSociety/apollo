package dev.pango.apollo.backend.modules.userauth.infraestructure.persistence.tables


// interface UserKtorm : Entity<UserKtorm> {
interface User {
//    companion object : Entity.Factory<UserKtorm>()
    val id: Int?
    var firstName: String
    var lastName: String
    var email: String
    var password: String
}

// object UserTable : Table<UserKtorm>("users") {
object UserTable {
//    val id = int("id").primaryKey().bindTo(UserKtorm::id)
//    val firstName = varchar("firstname").bindTo(UserKtorm::firstName)
//    val lastName = varchar("lastname").bindTo(UserKtorm::lastName)
//    val email = varchar("email").bindTo(UserKtorm::email)
//    val password = varchar("password").bindTo(UserKtorm::password)

}
