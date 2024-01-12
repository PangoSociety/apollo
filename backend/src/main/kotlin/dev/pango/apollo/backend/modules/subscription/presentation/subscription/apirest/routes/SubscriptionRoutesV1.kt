package dev.pango.apollo.backend.modules.subscription.presentation.subscription.apirest.routes

import dev.pango.apollo.backend.framework.http.getLocale
import dev.pango.apollo.backend.framework.http.respondEither
import dev.pango.apollo.backend.modules.sharedkernel.presentation.apirest.config.ApiRestConstants
import dev.pango.apollo.backend.modules.subscription.domain.entity.Subscription
import dev.pango.apollo.backend.modules.subscription.presentation.subscription.apirest.config.SUBSCRIPTIONS
import dev.pango.apollo.backend.modules.subscription.presentation.subscription.apirest.dto.UpdateSubscriptionDTO
import dev.pango.apollo.backend.modules.subscription.presentation.subscription.apirest.facade.SubscriptionServiceFacade
import dev.pango.apollo.backend.modules.subscription.presentation.subscription.translation.toTranslatableFailure
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.patch
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import org.koin.ktor.ext.inject
import java.util.UUID

fun Route.subscriptionRoutesV1() {
    val subscriptionServiceFacade by inject<SubscriptionServiceFacade>()

    route("/${ApiRestConstants.Resources.SUBSCRIPTIONS}") {
        get {
            val locale = call.request.getLocale()
            val subscription = subscriptionServiceFacade.listAllSubscriptions()
            val subscriptionListEitherTransformed = subscription.mapLeft { it.toTranslatableFailure(locale) }
            call.respondEither(HttpStatusCode.Found, subscriptionListEitherTransformed)
        }

        post {
            val locale = call.request.getLocale()
            val subscriptionCreated = subscriptionServiceFacade.createSubscription(call.receive())
            val subscriptionCreatedEitherTransformed =
                subscriptionCreated.mapLeft { it.toTranslatableFailure(locale) }
            call.respondEither(HttpStatusCode.Created, subscriptionCreatedEitherTransformed)
        }

        delete("/{id}") {
            val idString: String? = call.parameters["id"]
            val idUUID: UUID = UUID.fromString(idString)
            val locale = call.request.getLocale()
            val subscriptionDelete = subscriptionServiceFacade.deleteSubscription(idUUID)
            val subscriptionDeleteEitherTransformed =
                subscriptionDelete.mapLeft {
                    it.toTranslatableFailure(locale)
                }
            call.respondEither(HttpStatusCode.NoContent, subscriptionDeleteEitherTransformed)
        }

        patch("/{id}") {
            val idString: String? = call.parameters["id"]
            val idUUID: UUID = UUID.fromString(idString)
            val entity = call.receive<UpdateSubscriptionDTO>()
            val locale = call.request.getLocale()
            val subscriptionUpdate =
                subscriptionServiceFacade.updateSubscription(
                    Subscription(
                        id = idUUID,
                        courseId = entity.courseId,
                        startDate = entity.startDate,
                        endDate = entity.endDate,
                    ),
                )
            val subscriptionUpdateEitherTransformed =
                subscriptionUpdate.mapLeft {
                    it.toTranslatableFailure(locale)
                }
            call.respondEither(HttpStatusCode.OK, subscriptionUpdateEitherTransformed)
        }
    }
}
