package jlmd.dev.android.pruebaceiba.core.di

import jlmd.dev.android.pruebaceiba.core.UsersRepository
import org.koin.dsl.module

val repositoriesModule = module {

    single { UsersRepository(get(), get()) }

}