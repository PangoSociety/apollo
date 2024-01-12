package dev.pango.apollo.backend.modules.subscription.domain.entity

import java.time.*
import java.util.*

data class Subscription(
    val id: UUID = UUID.randomUUID(),
//    var userId : UUID,
    var courseId: UUID,
    var startDate: LocalDate,
    var endDate: LocalDate,
)
