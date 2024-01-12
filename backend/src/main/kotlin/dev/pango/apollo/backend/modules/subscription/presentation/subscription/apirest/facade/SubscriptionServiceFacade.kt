package dev.pango.apollo.backend.modules.subscription.presentation.subscription.apirest.facade

import arrow.core.Either
import dev.pango.apollo.backend.modules.subscription.application.SubscriptionApplicationService
import dev.pango.apollo.backend.modules.subscription.domain.entity.Subscription
import dev.pango.apollo.backend.modules.subscription.domain.failure.*
import dev.pango.apollo.backend.modules.subscription.mapper.toSubscription
import dev.pango.apollo.backend.modules.subscription.mapper.toSubscriptionDTO
import dev.pango.apollo.backend.modules.subscription.mapper.toSubscriptionDTOList
import dev.pango.apollo.backend.modules.subscription.presentation.subscription.apirest.dto.RegisterSubscriptionDTO
import dev.pango.apollo.backend.modules.subscription.presentation.subscription.apirest.dto.SubscriptionDTO
import java.util.UUID

class SubscriptionServiceFacade(
    private val subscriptionService: SubscriptionApplicationService,
) {
    fun listAllSubscriptions(): Either<SubscriptionDomainFailure, List<SubscriptionDTO>> {
        return subscriptionService.listAllSubscriptions().map { it.toSubscriptionDTOList() }
    }

    fun createSubscription(registerSubscriptionDTO: RegisterSubscriptionDTO): Either<SubscriptionDomainFailure, SubscriptionDTO> {
        return subscriptionService.createSubscription(registerSubscriptionDTO.toSubscription()).map {
            it.toSubscriptionDTO()
        }
    }

    fun deleteSubscription(id: UUID): Either<SubscriptionDomainFailure, Unit> {
        return subscriptionService.deleteSubscription(id)
    }

    fun updateSubscription(subscription: Subscription): Either<SubscriptionDomainFailure, SubscriptionDTO> {
        return subscriptionService.updateSubscription(subscription).map {
            it.toSubscriptionDTO()
        }
    }
}
