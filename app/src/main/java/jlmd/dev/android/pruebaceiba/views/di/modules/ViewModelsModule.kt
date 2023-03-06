package jlmd.dev.android.pruebaceiba.views.di.modules

import jlmd.dev.android.pruebaceiba.views.users.viewmodel.MainViewModel
import jlmd.dev.android.pruebaceiba.views.postsuser.viewmodel.PostsUserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {

    viewModel { MainViewModel(get()) }

    viewModel { PostsUserViewModel(get(), get()) }
}