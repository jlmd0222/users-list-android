package jlmd.dev.android.pruebaceiba.data.di

import android.app.Application
import jlmd.dev.android.pruebaceiba.data.database.UsersDatabase
import jlmd.dev.android.pruebaceiba.data.database.gateway.OfflineStorageGateway
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    fun provideDatabase(application: Application) = UsersDatabase.getDatabase(application)

    fun provideUsersDao(database: UsersDatabase) = database.userDao

    // Database
    single { provideDatabase(androidApplication()) }

    // DAOs
    single { provideUsersDao(get()) }

    // Gateway
    single { OfflineStorageGateway(get()) }
}