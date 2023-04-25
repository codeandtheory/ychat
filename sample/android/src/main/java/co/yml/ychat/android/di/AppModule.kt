package co.yml.ychat.android.di

import co.yml.ychat.YChat
import co.yml.ychat.android.BuildConfig
import co.yml.ychat.android.data.ProviderKey
import co.yml.ychat.android.data.ProviderRepository
import co.yml.ychat.android.presentation.chatcompletions.viewmodel.ChatCompletionsViewModel
import co.yml.ychat.android.presentation.completions.CompletionsViewModel
import co.yml.ychat.android.presentation.edits.EditsViewModel
import co.yml.ychat.android.presentation.home.viewmodel.HomeViewModel
import co.yml.ychat.android.presentation.images.ImagesViewModel
import co.yml.ychat.android.presentation.models.viewmodel.ModelsViewModel
import co.yml.ychat.android.presentation.settings.SettingsScreenViewModel
import co.yml.ychat.android.usecases.GetSelectedProviderKeyUseCase
import co.yml.ychat.android.usecases.GetSelectedProviderUseCase
import co.yml.ychat.android.usecases.SelectProviderUseCase
import co.yml.ychat.ducai.entrypoint.DucAI
import co.yml.ychat.ducai.entrypoint.DucAIImpl
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single { YChat.create(BuildConfig.API_KEY) }

    single<DucAI> { DucAIImpl() }

    factory {
        ProviderRepository(
            mapOf(
                ProviderKey.OPENAI to get<YChat>(),
                ProviderKey.DUCAI to get<DucAI>()
            ),
            get()
        )
    }

    factory { GetSelectedProviderKeyUseCase(get(), Dispatchers.Default) }
    factory { SelectProviderUseCase(get()) }
    factory { GetSelectedProviderUseCase(get(), Dispatchers.Default) }

    viewModelOf(::HomeViewModel)
    viewModelOf(::ChatCompletionsViewModel)
    viewModelOf(::ModelsViewModel)
    viewModelOf(::CompletionsViewModel)
    viewModelOf(::EditsViewModel)
    viewModelOf(::ImagesViewModel)
    viewModelOf(::SettingsScreenViewModel)

}