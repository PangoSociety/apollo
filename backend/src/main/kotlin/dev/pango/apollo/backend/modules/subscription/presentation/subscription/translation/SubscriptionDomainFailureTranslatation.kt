package dev.pango.apollo.backend.modules.subscription.presentation.subscription.translation

import dev.pango.apollo.backend.modules.sharedkernel.infraestructure.intl.TranslatableFailure
import dev.pango.apollo.backend.modules.subscription.domain.failure.SubscriptionDomainFailure
import java.util.Locale

fun SubscriptionDomainFailure.toTranslatableFailure(locale: Locale): TranslatableFailure =
    when (this) {
        SubscriptionDomainFailure.SubscriptionListNotAvailable -> TranslatableFailure(Subscription.failureSubscriptionListNotAvailable(locale))
        // TODO
        SubscriptionDomainFailure.SubscriptionDeleteNotAvailable -> TranslatableFailure(Subscription.failureSubscriptionListNotAvailable(locale))
        SubscriptionDomainFailure.SubscriptionUpdateNotAvailable -> TranslatableFailure(Subscription.failureSubscriptionListNotAvailable(locale))
    }
