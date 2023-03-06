package jlmd.dev.android.pruebaceiba

import android.app.Application
import jlmd.dev.android.pruebaceiba.core.di.repositoriesModule
import jlmd.dev.android.pruebaceiba.core.di.useCaseModules
import jlmd.dev.android.pruebaceiba.data.di.databaseModule
import jlmd.dev.android.pruebaceiba.data.di.networkModule
import jlmd.dev.android.pruebaceiba.data.di.serviceModule
import jlmd.dev.android.pruebaceiba.views.di.modules.viewModelsModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

private val appModules = listOf<Module>() +
    viewModelsModule +
    networkModule +
    serviceModule +
    databaseModule +
    repositoriesModule +
    useCaseModules

@ExperimentalCoroutinesApi
class UsersApplication : Application() {

    private val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate() {
        super.onCreate()

        initDI()
    }

    private fun initDI() {
        val applicationCoroutineScopeModule = module {
            single { applicationScope }
        }

        startKoin {
            androidContext(this@UsersApplication)
            modules(
                appModules + applicationCoroutineScopeModule
            )
        }
    }
}