package jlmd.dev.android.pruebaceiba.core.di

import jlmd.dev.android.pruebaceiba.core.usecases.GetPostsByUserUseCase
import jlmd.dev.android.pruebaceiba.core.usecases.GetUserByIdUseCase
import jlmd.dev.android.pruebaceiba.core.usecases.GetUsersUseCase
import org.koin.dsl.module

val useCaseModules = module {

    factory { GetUsersUseCase(get()) }

    factory { GetUserByIdUseCase(get()) }

    factory { GetPostsByUserUseCase(get()) }

}