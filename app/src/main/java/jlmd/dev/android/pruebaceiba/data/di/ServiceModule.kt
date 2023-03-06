package jlmd.dev.android.pruebaceiba.data.di

import jlmd.dev.android.pruebaceiba.data.service.UsersAPI
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val serviceModule = module {

    single<UsersAPI> {
        provideService(get(named(RetrofitClients.USERS_API.name)))
    }
}

private inline fun <reified T> provideService(retrofit: Retrofit): T = retrofit.create(T::class.java)
