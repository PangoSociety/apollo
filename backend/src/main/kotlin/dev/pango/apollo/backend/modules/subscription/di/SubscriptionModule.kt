package dev.pango.apollo.backend.modules.subscription.di

import dev.pango.apollo.backend.modules.subscription.application.SubscriptionApplicationService
import dev.pango.apollo.backend.modules.subscription.domain.repository.SubscriptionRepository
import dev.pango.apollo.backend.modules.subscription.domain.usecase.DeleteSubscriptionUseCase
import dev.pango.apollo.backend.modules.subscription.domain.usecase.RegisterSubscriptionUseCase
import dev.pango.apollo.backend.modules.subscription.domain.usecase.ShowSubscriptionListUseCase
import dev.pango.apollo.backend.modules.subscription.domain.usecase.UpdateSubscriptionUseCase
import dev.pango.apollo.backend.modules.subscription.infraestructure.persistence.SubscriptionRepositoryExposed
import dev.pango.apollo.backend.modules.subscription.presentation.subscription.apirest.facade.SubscriptionServiceFacade
import org.koin.dsl.module

val subscriptionModule =
    module {
        single<SubscriptionRepository> { SubscriptionRepositoryExposed() }

        single<SubscriptionApplicationService> { SubscriptionApplicationService(get(), get(), get(), get()) }

        single { SubscriptionServiceFacade(get()) }

        single<ShowSubscriptionListUseCase> { ShowSubscriptionListUseCase(get()) }
        single<DeleteSubscriptionUseCase> { DeleteSubscriptionUseCase(get()) }
        single<UpdateSubscriptionUseCase> { UpdateSubscriptionUseCase(get()) }
        single<RegisterSubscriptionUseCase> { RegisterSubscriptionUseCase(get()) }
    }
