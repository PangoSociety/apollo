package dev.pango.apollo.backend.modules.subscription.domain.failure

import dev.pango.apollo.backend.modules.sharedkernel.domain.failure.*

sealed class SubscriptionDomainFailure : Failure {
    data object SubscriptionListNotAvailable : SubscriptionDomainFailure()

    data object SubscriptionDeleteNotAvailable : SubscriptionDomainFailure()

    data object SubscriptionUpdateNotAvailable : SubscriptionDomainFailure()
}
