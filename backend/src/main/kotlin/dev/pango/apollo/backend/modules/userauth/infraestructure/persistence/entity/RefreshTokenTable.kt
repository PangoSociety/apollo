package dev.pango.apollo.backend.modules.userauth.infraestructure.persistence.entity

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column

object RefreshTokenTable : IdTable<String>() {
    override val id: Column<EntityID<String>> = varchar("refresh_token", 255).entityId()
    val email = RefreshTokenTable.varchar("email", 50)
    override val primaryKey: PrimaryKey = PrimaryKey(RefreshTokenTable.id)
}

class RefreshTokenTableEntity(id: EntityID<String>) : Entity<String>(id) {
    companion object : EntityClass<String, RefreshTokenTableEntity>(RefreshTokenTable)

    var email by RefreshTokenTable.email
}
