package dev.pango.apollo.backend.modules.subscription.application

import arrow.core.Either
import dev.pango.apollo.backend.modules.subscription.domain.entity.Subscription
import dev.pango.apollo.backend.modules.subscription.domain.failure.SubscriptionDomainFailure
import dev.pango.apollo.backend.modules.subscription.domain.usecase.DeleteSubscriptionUseCase
import dev.pango.apollo.backend.modules.subscription.domain.usecase.RegisterSubscriptionUseCase
import dev.pango.apollo.backend.modules.subscription.domain.usecase.ShowSubscriptionListUseCase
import dev.pango.apollo.backend.modules.subscription.domain.usecase.UpdateSubscriptionUseCase
import java.util.UUID

class SubscriptionApplicationService(
    private val subscriptionListUseCase: ShowSubscriptionListUseCase,
    private val registerSubscriptionUseCase: RegisterSubscriptionUseCase,
    private val deleteSubscriptionUseCase: DeleteSubscriptionUseCase,
    private val updateSubscriptionUseCase: UpdateSubscriptionUseCase,
) {
    fun listAllSubscriptions(): Either<SubscriptionDomainFailure, List<Subscription>> {
        return subscriptionListUseCase()
    }

    fun createSubscription(subscription: Subscription): Either<SubscriptionDomainFailure, Subscription> {
        return registerSubscriptionUseCase(subscription)
    }

    fun deleteSubscription(id: UUID): Either<SubscriptionDomainFailure, Unit> {
        return deleteSubscriptionUseCase(id)
    }

    fun updateSubscription(subscription: Subscription): Either<SubscriptionDomainFailure, Subscription> {
        return updateSubscriptionUseCase(subscription)
    }
}
