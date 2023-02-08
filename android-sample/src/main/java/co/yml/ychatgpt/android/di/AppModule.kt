package co.yml.ychatgpt.android.di

import co.yml.ychatgpt.ChatGpt
import co.yml.ychatgpt.android.BuildConfig
import co.yml.ychatgpt.android.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single { ChatGpt.create(BuildConfig.API_KEY) }
    viewModelOf(::MainViewModel)
}