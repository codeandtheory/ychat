package co.yml.openai.provider.entrypoint.impl

import co.yml.openai.provider.OpenAi
import co.yml.openai.provider.entrypoint.features.OpenAiAudioTranslations
import co.yml.ychat.core.model.FileBytes
import co.yml.openai.provider.domain.model.AudioParams
import co.yml.openai.provider.domain.usecases.AudioUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

internal class OpenAiAudioTranslationsImpl(
    private val dispatcher: CoroutineDispatcher,
    private val audioUseCase: AudioUseCase,
) : OpenAiAudioTranslations {

    private val scope by lazy { CoroutineScope(SupervisorJob() + dispatcher) }

    private val params: AudioParams = AudioParams()

    override fun setModel(model: String): OpenAiAudioTranslations {
        params.model = model
        return this
    }

    override fun setPrompt(prompt: String): OpenAiAudioTranslations {
        params.prompt = prompt
        return this
    }

    override fun setResponseFormat(format: String): OpenAiAudioTranslations {
        params.responseFormat = format
        return this
    }

    override fun setTemperature(temperature: Double): OpenAiAudioTranslations {
        params.temperature = temperature
        return this
    }

    override suspend fun execute(filename: String, audioFile: FileBytes): String {
        return audioUseCase.requestAudioTranslations(filename, audioFile, params)
    }

    override fun execute(filename: String, audioFile: FileBytes, callback: OpenAi.Callback<String>) {
        scope.launch {
            runCatching { execute(filename, audioFile) }
                .onSuccess { callback.onSuccess(it) }
                .onFailure { callback.onError(it) }
        }
    }
}
