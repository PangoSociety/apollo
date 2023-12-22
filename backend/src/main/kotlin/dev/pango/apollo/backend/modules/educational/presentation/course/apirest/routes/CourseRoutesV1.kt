package dev.pango.apollo.backend.modules.educational.presentation.course.apirest.routes

import dev.pango.apollo.backend.*
import dev.pango.apollo.backend.framework.http.*
import dev.pango.apollo.backend.modules.educational.presentation.course.apirest.config.*
import dev.pango.apollo.backend.modules.educational.presentation.course.apirest.facade.*
import dev.pango.apollo.backend.modules.educational.presentation.course.translation.*
import dev.pango.apollo.backend.modules.sharedkernel.presentation.apirest.config.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.*

fun Route.courseRoutesV1() {
    val courseServiceFacade by inject<CourseServiceFacade>()
    route("/${ApiRestConstants.Resources.COURSES}") {
        get() {
            val locale = call.request.getLocale()
            val courses = courseServiceFacade.listAllCourses()
            val courseListEitherTransformed = courses.mapLeft { it.toTranslatableFailure(locale) }
            call.respondEither(HttpStatusCode.Found, courseListEitherTransformed)
        }
    }
}

