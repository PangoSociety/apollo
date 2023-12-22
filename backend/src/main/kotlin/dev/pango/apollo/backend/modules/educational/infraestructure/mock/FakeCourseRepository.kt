package dev.pango.apollo.backend.modules.educational.infraestructure.mock

import arrow.core.*
import dev.pango.apollo.backend.framework.testing.*
import dev.pango.apollo.backend.modules.educational.domain.entity.*
import dev.pango.apollo.backend.modules.educational.domain.repository.*
import io.github.serpro69.kfaker.*
import io.ktor.util.logging.*
import org.slf4j.*
import org.slf4j.Logger
import kotlin.random.*

inline fun <reified T> T.getLogger(): Logger {
    return LoggerFactory.getLogger(T::class.java)
}

fun <Right, Left> Either<Throwable, Right>.mapLeftLogged(function: (Throwable) -> Left): Either<Left, Right> {
    return mapLeft { throwable ->
        getLogger().error(throwable)
        function(throwable)
    }
}

class FakeCourseRepository : CourseRepository {

    private val faker = Faker()
    private val defaultInitCourses = List(10) { Course(it, faker.educator.courseName()) }
    private val courses = mutableListOf(*defaultInitCourses.toTypedArray())

    override fun getCourseList(): Either<RepositoryFailure, List<Course>> = Either.catch {
        if (Random.generateDecision(0.2)) {
            throw RuntimeException("Data access not accessible")
        }
        courses
    }.mapLeftLogged {
        RepositoryFailure.DataSourceAccessException(it)
    }
}