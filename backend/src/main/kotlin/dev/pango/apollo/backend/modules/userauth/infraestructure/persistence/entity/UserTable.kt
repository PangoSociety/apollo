package dev.pango.apollo.backend.modules.userauth.infraestructure.persistence.entity

import org.jetbrains.exposed.dao.*
import org.jetbrains.exposed.dao.id.*
import org.jetbrains.exposed.sql.Column
import java.util.UUID

object UserTable : IdTable<UUID>() {
    override val id: Column<EntityID<UUID>> = uuid("user_id").entityId()
    val userName = varchar("user_name", 50)
    val firstName = varchar("first_name", 50)
    val lastName = varchar("last_name", 50)
    val email = varchar("email", 100)
    val password = varchar("password", 255)
    override val primaryKey: PrimaryKey = PrimaryKey(id)
}

class UserTableEntity(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<UserTableEntity>(UserTable)

    var username by UserTable.userName
    var firstname by UserTable.firstName
    var lastname by UserTable.lastName
    var email by UserTable.email
    var password by UserTable.password
}
