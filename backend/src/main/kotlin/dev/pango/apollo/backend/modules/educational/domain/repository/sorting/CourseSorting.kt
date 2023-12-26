package dev.pango.apollo.backend.modules.educational.domain.repository.sorting

import dev.pango.apollo.backend.modules.sharedkernel.domain.repository.sorting.*

data class CourseSorting(
    val sortField: CourseSortField,
    val sortType: SortType,
)
