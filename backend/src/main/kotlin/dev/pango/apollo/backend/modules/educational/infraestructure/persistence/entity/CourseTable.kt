package dev.pango.apollo.backend.modules.educational.infraestructure.persistence.entity

import org.jetbrains.exposed.dao.*
import org.jetbrains.exposed.dao.id.*
import org.jetbrains.exposed.sql.*
import java.util.*

object CourseTable : IdTable<UUID>() {
    override val id: Column<EntityID<UUID>> = uuid("id").entityId()
    val name = varchar("name", 50)
    override val primaryKey: PrimaryKey = PrimaryKey(id)
}

// class CourseTableEntity(id: EntityID<UUID>) : UUIDEntity(id) {
//    companion object : UUIDEntityClass<CourseTableEntity>(CourseTable)
//
//    var name by CourseTable.name
// }
class CourseTableEntity(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<CourseTableEntity>(CourseTable)

    var name by CourseTable.name
}
