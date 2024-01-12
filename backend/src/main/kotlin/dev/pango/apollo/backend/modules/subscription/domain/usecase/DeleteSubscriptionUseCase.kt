package dev.pango.apollo.backend.modules.subscription.domain.usecase

import arrow.core.Either
import dev.pango.apollo.backend.modules.sharedkernel.domain.repository.RepositoryFailure
import dev.pango.apollo.backend.modules.subscription.domain.failure.SubscriptionDomainFailure
import dev.pango.apollo.backend.modules.subscription.domain.repository.SubscriptionRepository
import java.util.UUID

class DeleteSubscriptionUseCase(
    private val subscriptionRepository: SubscriptionRepository,
) {
    operator fun invoke(id: UUID): Either<SubscriptionDomainFailure, Unit> {
        return subscriptionRepository.deleteSubscription(id).mapLeft {
            when (it) {
                is RepositoryFailure.DataSourceAccessException -> SubscriptionDomainFailure.SubscriptionDeleteNotAvailable
            }
        }
    }
}
