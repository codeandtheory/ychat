package co.yml.ychat.entrypoint.impl

import co.yml.ychat.YChat
import co.yml.ychat.core.model.FileBytes
import co.yml.ychat.domain.model.AudioParams
import co.yml.ychat.domain.usecases.AudioUseCase
import co.yml.ychat.entrypoint.features.AudioTranslations
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

internal class AudioTranslationsImpl(
    private val dispatcher: CoroutineDispatcher,
    private val audioUseCase: AudioUseCase,
) : AudioTranslations {

    private val scope by lazy { CoroutineScope(SupervisorJob() + dispatcher) }

    private val params: AudioParams = AudioParams()

    override fun setModel(model: String): AudioTranslations {
        params.model = model
        return this
    }

    override fun setPrompt(prompt: String): AudioTranslations {
        params.prompt = prompt
        return this
    }

    override fun setResponseFormat(format: String): AudioTranslations {
        params.responseFormat = format
        return this
    }

    override fun setTemperature(temperature: Double): AudioTranslations {
        params.temperature = temperature
        return this
    }

    override suspend fun execute(filename: String, audioFile: FileBytes): String {
        return audioUseCase.requestAudioTranslations(filename, audioFile, params)
    }

    override fun execute(filename: String, audioFile: FileBytes, callback: YChat.Callback<String>) {
        scope.launch {
            runCatching { execute(filename, audioFile) }
                .onSuccess { callback.onSuccess(it) }
                .onFailure { callback.onError(it) }
        }
    }
}
