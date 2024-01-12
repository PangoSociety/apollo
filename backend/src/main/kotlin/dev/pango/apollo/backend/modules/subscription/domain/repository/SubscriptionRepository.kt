package dev.pango.apollo.backend.modules.subscription.domain.repository

import arrow.core.Either
import dev.pango.apollo.backend.modules.sharedkernel.domain.repository.RepositoryFailure
import dev.pango.apollo.backend.modules.subscription.domain.entity.Subscription
import java.util.UUID

interface SubscriptionRepository {
    fun getSubscription(): Either<RepositoryFailure, List<Subscription>>

    fun createSubscription(subscription: Subscription): Either<RepositoryFailure, Subscription>

    fun deleteSubscription(id: UUID): Either<RepositoryFailure, Unit>

    fun updateSubscription(subscription: Subscription): Either<RepositoryFailure, Subscription>
}
