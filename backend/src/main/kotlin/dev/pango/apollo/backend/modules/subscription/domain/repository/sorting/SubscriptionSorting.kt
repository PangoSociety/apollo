package dev.pango.apollo.backend.modules.subscription.domain.repository.sorting

import dev.pango.apollo.backend.modules.sharedkernel.domain.repository.sorting.SortType

data class SubscriptionSorting(
    val sortField: SubscriptionSortField,
    val sortType: SortType,
)
