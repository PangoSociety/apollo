package dev.pango.apollo.backend.framework.functional

import arrow.core.*
import dev.pango.apollo.backend.modules.sharedkernel.infraestructure.logger.*
import io.ktor.util.logging.*

fun <Right, Left> Either<Throwable, Right>.mapLeftLogged(function: (Throwable) -> Left): Either<Left, Right> {
    return mapLeft { throwable ->
//        getLogger().error(throwable)
        function(throwable)
    }
}
