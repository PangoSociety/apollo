package dev.pango.apollo.backend.modules.subscription.domain.usecase

import arrow.core.Either
import dev.pango.apollo.backend.modules.sharedkernel.domain.repository.*
import dev.pango.apollo.backend.modules.subscription.domain.entity.*
import dev.pango.apollo.backend.modules.subscription.domain.failure.SubscriptionDomainFailure
import dev.pango.apollo.backend.modules.subscription.domain.repository.*

class RegisterSubscriptionUseCase(
    private val subscriptionRepository: SubscriptionRepository,
) {
    operator fun invoke(subscription: Subscription): Either<SubscriptionDomainFailure, Subscription> {
        return subscriptionRepository.createSubscription(subscription).mapLeft {
            when (it) {
                is RepositoryFailure.DataSourceAccessException -> SubscriptionDomainFailure.SubscriptionListNotAvailable
            }
        }
    }
}
