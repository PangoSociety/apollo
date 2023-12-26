package dev.pango.apollo.backend.modules.sharedkernel.di

import dev.pango.apollo.backend.modules.sharedkernel.infraestructure.config.*
import org.koin.dsl.*

val sharedKernelModule =
    module {
        single { ApolloConfig(get()) }
    }
