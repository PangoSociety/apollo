package dev.pango.apollo.backend.modules.subscription.mapper

import dev.pango.apollo.backend.modules.subscription.domain.entity.*
import dev.pango.apollo.backend.modules.subscription.infraestructure.persistence.entity.*

fun SubscriptionTableEntity.toSubscription() =
    Subscription(
        id = id.value,
        courseId = course.value,
        startDate = startDate,
        endDate = endDate,
    )
