package dev.pango.apollo.backend.modules.educational.presentation.course.apirest.routes

import dev.pango.apollo.backend.framework.http.*
import dev.pango.apollo.backend.modules.educational.domain.entity.Course
import dev.pango.apollo.backend.modules.educational.presentation.course.apirest.config.*
import dev.pango.apollo.backend.modules.educational.presentation.course.apirest.dto.UpdateCourseDTO
import dev.pango.apollo.backend.modules.educational.presentation.course.apirest.facade.*
import dev.pango.apollo.backend.modules.educational.presentation.course.translation.*
import dev.pango.apollo.backend.modules.sharedkernel.presentation.apirest.config.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.*
import java.util.UUID

fun Route.courseRoutesV1() {
    val courseServiceFacade by inject<CourseServiceFacade>()
    route("/${ApiRestConstants.Resources.COURSES}") {
        get {
            val locale = call.request.getLocale()
            val courses = courseServiceFacade.listAllCourses()
            val courseListEitherTransformed = courses.mapLeft { it.toTranslatableFailure(locale) }
            call.respondEither(HttpStatusCode.Found, courseListEitherTransformed)
        }

        post {
            val locale = call.request.getLocale()
            val courseCreated = courseServiceFacade.createCourse(call.receive())
            val courseCreatedEitherTransformed =
                courseCreated.mapLeft { it.toTranslatableFailure(locale) }
            call.respondEither(HttpStatusCode.Created, courseCreatedEitherTransformed)
        }

        delete("/{id}") {
            val idString: String? = call.parameters["id"]
            val idUUID: UUID = UUID.fromString(idString)
            val locale = call.request.getLocale()
            val courseDelete = courseServiceFacade.deleteCourse(idUUID)
            val courseDeleteEitherTransformed =
                courseDelete.mapLeft {
                    it.toTranslatableFailure(locale)
                }
            call.respondEither(HttpStatusCode.NoContent, courseDeleteEitherTransformed)
        }

        patch("/{id}") {
            val idString: String? = call.parameters["id"]
            val idUUID: UUID = UUID.fromString(idString)
            val entity = call.receive<UpdateCourseDTO>()
            val locale = call.request.getLocale()
            val courseUpdate =
                courseServiceFacade.updateCourse(
                    Course(
                        id = idUUID,
                        name = entity.name,
                    ),
                )
            val courseUpdateEitherTransformed =
                courseUpdate.mapLeft {
                    it.toTranslatableFailure(locale)
                }
            call.respondEither(HttpStatusCode.OK, courseUpdateEitherTransformed)
        }
    }
}
