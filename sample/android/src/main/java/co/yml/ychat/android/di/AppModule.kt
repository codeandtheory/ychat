package co.yml.ychat.android.di

import co.yml.ychat.YChat
import co.yml.ychat.android.BuildConfig
import co.yml.ychat.android.presentation.chatcompletions.viewmodel.ChatCompletionsViewModel
import co.yml.ychat.android.presentation.home.viewmodel.HomeViewModel
import co.yml.ychat.android.presentation.models.viewmodel.ModelsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single { YChat.create(BuildConfig.API_KEY) }
    viewModelOf(::HomeViewModel)
    viewModelOf(::ChatCompletionsViewModel)
    viewModelOf(::ModelsViewModel)
}