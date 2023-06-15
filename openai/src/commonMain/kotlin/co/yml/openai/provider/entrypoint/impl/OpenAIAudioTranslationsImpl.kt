package co.yml.openai.provider.entrypoint.impl

import co.yml.openai.provider.OpenAI
import co.yml.openai.provider.entrypoint.features.OpenAIAudioTranslations
import co.yml.ychat.core.model.FileBytes
import co.yml.openai.provider.domain.model.AudioParams
import co.yml.openai.provider.domain.usecases.AudioUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

internal class OpenAIAudioTranslationsImpl(
    private val dispatcher: CoroutineDispatcher,
    private val audioUseCase: AudioUseCase,
) : OpenAIAudioTranslations {

    private val scope by lazy { CoroutineScope(SupervisorJob() + dispatcher) }

    private val params: AudioParams = AudioParams()

    override fun setModel(model: String): OpenAIAudioTranslations {
        params.model = model
        return this
    }

    override fun setPrompt(prompt: String): OpenAIAudioTranslations {
        params.prompt = prompt
        return this
    }

    override fun setResponseFormat(format: String): OpenAIAudioTranslations {
        params.responseFormat = format
        return this
    }

    override fun setTemperature(temperature: Double): OpenAIAudioTranslations {
        params.temperature = temperature
        return this
    }

    override suspend fun execute(filename: String, audioFile: FileBytes): String {
        return audioUseCase.requestAudioTranslations(filename, audioFile, params)
    }

    override fun execute(filename: String, audioFile: FileBytes, callback: OpenAI.Callback<String>) {
        scope.launch {
            runCatching { execute(filename, audioFile) }
                .onSuccess { callback.onSuccess(it) }
                .onFailure { callback.onError(it) }
        }
    }
}
