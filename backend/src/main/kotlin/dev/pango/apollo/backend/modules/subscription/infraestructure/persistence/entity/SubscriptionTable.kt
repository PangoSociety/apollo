package dev.pango.apollo.backend.modules.subscription.infraestructure.persistence.entity

import dev.pango.apollo.backend.modules.educational.infraestructure.persistence.entity.CourseTable
import dev.pango.apollo.backend.modules.educational.infraestructure.persistence.entity.CourseTable.entityId
import org.jetbrains.exposed.dao.*
import org.jetbrains.exposed.dao.id.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.javatime.*
import java.util.*

object SubscriptionTable : IdTable<UUID>() {
    override val id: Column<EntityID<UUID>> = uuid("id").entityId()
    val courseId = reference("course_id", CourseTable)

    //    val userId = reference("user_id", UserTable)
    val startDate = date("start_date")
    val endDate = date("end_date")
    override val primaryKey: PrimaryKey = PrimaryKey(id)
}

class SubscriptionTableEntity(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<SubscriptionTableEntity>(SubscriptionTable)

    //    var course by CourseTableEntity referencedOn SubscriptionTable.courseId
    var course by SubscriptionTable.courseId
    var startDate by SubscriptionTable.startDate
    var endDate by SubscriptionTable.endDate
//    var user by UserEntity referencedOn SubscriptionTable.userId
}
