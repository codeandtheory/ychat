package co.yml.ychat.android.di

import co.yml.ychat.YChat
import co.yml.ychat.android.BuildConfig
import co.yml.ychat.android.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single { YChat.create(BuildConfig.API_KEY) }
    viewModelOf(::MainViewModel)
}