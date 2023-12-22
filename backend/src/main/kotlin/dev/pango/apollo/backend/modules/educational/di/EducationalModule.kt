package dev.pango.apollo.backend.modules.educational.di

import dev.pango.apollo.backend.modules.educational.application.*
import dev.pango.apollo.backend.modules.educational.domain.repository.*
import dev.pango.apollo.backend.modules.educational.domain.usecase.*
import dev.pango.apollo.backend.modules.educational.infraestructure.mock.*
import dev.pango.apollo.backend.modules.educational.presentation.course.apirest.facade.*
import org.koin.dsl.*

val educationalModule = module {
    // Repositories
    single<CourseRepository> { FakeCourseRepository() }
    // Application Services
    single<CourseApplicationService> { CourseApplicationService(get()) }
    // Facades
    single { CourseServiceFacade(get()) }
    // Use cases
    single<ShowCourseListUseCase> { ShowCourseListUseCase(get()) }
}
