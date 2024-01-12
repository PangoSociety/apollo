package dev.pango.apollo.backend.modules.subscription.mapper

import dev.pango.apollo.backend.modules.subscription.domain.entity.*
import dev.pango.apollo.backend.modules.subscription.presentation.subscription.apirest.dto.RegisterSubscriptionDTO

fun RegisterSubscriptionDTO.toSubscription() =
    Subscription(
        courseId = courseId,
        startDate = startDate,
        endDate = endDate,
    )
