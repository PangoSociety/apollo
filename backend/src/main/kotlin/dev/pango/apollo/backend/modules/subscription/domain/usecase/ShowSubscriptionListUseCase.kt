package dev.pango.apollo.backend.modules.subscription.domain.usecase

import arrow.core.Either
import dev.pango.apollo.backend.modules.educational.domain.failure.*
import dev.pango.apollo.backend.modules.sharedkernel.domain.repository.*
import dev.pango.apollo.backend.modules.subscription.domain.entity.*
import dev.pango.apollo.backend.modules.subscription.domain.failure.SubscriptionDomainFailure
import dev.pango.apollo.backend.modules.subscription.domain.repository.*

class ShowSubscriptionListUseCase(private val subscriptionRepository: SubscriptionRepository) {
    operator fun invoke(): Either<SubscriptionDomainFailure, List<Subscription>> {
        return subscriptionRepository.getSubscription().mapLeft {
            when (it) {
                is RepositoryFailure.DataSourceAccessException -> SubscriptionDomainFailure.SubscriptionListNotAvailable
            }
        }
    }
}
