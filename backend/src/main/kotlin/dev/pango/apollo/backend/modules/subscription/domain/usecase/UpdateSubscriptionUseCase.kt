package dev.pango.apollo.backend.modules.subscription.domain.usecase

import arrow.core.Either
import dev.pango.apollo.backend.modules.sharedkernel.domain.repository.RepositoryFailure
import dev.pango.apollo.backend.modules.subscription.domain.entity.Subscription
import dev.pango.apollo.backend.modules.subscription.domain.failure.SubscriptionDomainFailure
import dev.pango.apollo.backend.modules.subscription.domain.repository.SubscriptionRepository

class UpdateSubscriptionUseCase(
    private val subscriptionRepository: SubscriptionRepository,
) {
    operator fun invoke(subscription: Subscription): Either<SubscriptionDomainFailure, Subscription> {
        return subscriptionRepository.updateSubscription(subscription).mapLeft {
            when (it) {
                is RepositoryFailure.DataSourceAccessException -> SubscriptionDomainFailure.SubscriptionUpdateNotAvailable
            }
        }
    }
}
