package co.yml.ychat.entrypoint.impl

import co.yml.ychat.YChat
import co.yml.ychat.domain.model.AudioParams
import co.yml.ychat.domain.model.FileBytes
import co.yml.ychat.domain.usecases.AudioUseCase
import co.yml.ychat.entrypoint.features.AudioTranscriptions
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

internal class AudioTranscriptionsImpl(
    private val dispatcher: CoroutineDispatcher,
    private val audioUseCase: AudioUseCase,
) : AudioTranscriptions {

    private val scope by lazy { CoroutineScope(SupervisorJob() + dispatcher) }

    private val params: AudioParams = AudioParams()

    override fun setModel(model: String): AudioTranscriptions {
        params.model = model
        return this
    }

    override fun setPrompt(prompt: String): AudioTranscriptions {
        params.prompt = prompt
        return this
    }

    override fun setResponseFormat(format: String): AudioTranscriptions {
        params.responseFormat = format
        return this
    }

    override fun setTemperature(temperature: Double): AudioTranscriptions {
        params.temperature = temperature
        return this
    }

    override fun setLanguage(language: String): AudioTranscriptions {
        params.language = language
        return this
    }

    override suspend fun execute(filename: String, audioFile: FileBytes): String {
        return audioUseCase.requestAudioTranscription(filename, audioFile, params)
    }

    override fun execute(filename: String, audioFile: FileBytes, callback: YChat.Callback<String>) {
        scope.launch {
            runCatching { execute(filename, audioFile) }
                .onSuccess { callback.onSuccess(it) }
                .onFailure { callback.onError(it) }
        }
    }
}
