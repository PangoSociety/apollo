package dev.pango.apollo.backend.modules.subscription.infraestructure.persistence

import arrow.core.Either
import dev.pango.apollo.backend.framework.functional.mapLeftLogged
import dev.pango.apollo.backend.modules.sharedkernel.domain.repository.RepositoryFailure
import dev.pango.apollo.backend.modules.subscription.domain.entity.Subscription
import dev.pango.apollo.backend.modules.subscription.domain.repository.SubscriptionRepository
import dev.pango.apollo.backend.modules.subscription.infraestructure.persistence.entity.SubscriptionTable
import dev.pango.apollo.backend.modules.subscription.infraestructure.persistence.entity.SubscriptionTableEntity
import dev.pango.apollo.backend.modules.subscription.mapper.toSubscription
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.UUID

class SubscriptionRepositoryExposed : SubscriptionRepository {
    override fun getSubscription(): Either<RepositoryFailure, List<Subscription>> =
        Either.catch {
            transaction {
                SubscriptionTableEntity.all().map { it.toSubscription() }
            }
        }.mapLeftLogged {
            RepositoryFailure.DataSourceAccessException(it)
        }

    override fun createSubscription(subscription: Subscription): Either<RepositoryFailure, Subscription> =
        Either.catch {
            val newSubscription =
                transaction {
                    SubscriptionTableEntity.new(subscription.id) {
                        startDate = subscription.startDate
                        endDate = subscription.endDate
                    }
                }
            newSubscription.toSubscription()
        }.mapLeftLogged {
            RepositoryFailure.DataSourceAccessException(it)
        }

    override fun deleteSubscription(id: UUID): Either<RepositoryFailure, Unit> =
        Either.catch {
            transaction {
                SubscriptionTableEntity.findById(id)?.delete()
            }
            Unit
        }.mapLeftLogged {
            RepositoryFailure.DataSourceAccessException(it)
        }

    override fun updateSubscription(subscription: Subscription): Either<RepositoryFailure, Subscription> =
        Either.catch {
            transaction {
                val subscriptionEntity =
                    SubscriptionTableEntity.findById(EntityID(subscription.id, SubscriptionTable))!!
                subscriptionEntity.startDate = subscription.startDate
                subscriptionEntity.endDate = subscription.endDate
                subscriptionEntity
            }.toSubscription()
        }.mapLeftLogged {
            RepositoryFailure.DataSourceAccessException(it)
        }
}
