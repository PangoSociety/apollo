package dev.pango.apollo.backend.modules.subscription.mapper

import dev.pango.apollo.backend.modules.subscription.domain.entity.Subscription
import dev.pango.apollo.backend.modules.subscription.presentation.subscription.apirest.dto.SubscriptionDTO

fun Subscription.toSubscriptionDTO() =
    SubscriptionDTO(
        id = id,
//    userId = userId,
        courseId = courseId,
        startDate = startDate,
        endDate = endDate,
    )

fun List<Subscription>.toSubscriptionDTOList() = map { it.toSubscriptionDTO() }
