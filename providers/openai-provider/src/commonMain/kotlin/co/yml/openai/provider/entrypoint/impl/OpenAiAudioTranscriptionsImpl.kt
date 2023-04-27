package co.yml.openai.provider.entrypoint.impl

import co.yml.openai.provider.OpenAi
import co.yml.openai.provider.entrypoint.features.OpenAiAudioTranscriptions
import co.yml.ychat.core.model.FileBytes
import co.yml.openai.provider.domain.model.AudioParams
import co.yml.openai.provider.domain.usecases.AudioUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

internal class OpenAiAudioTranscriptionsImpl(
    private val dispatcher: CoroutineDispatcher,
    private val audioUseCase: AudioUseCase,
) : OpenAiAudioTranscriptions {

    private val scope by lazy { CoroutineScope(SupervisorJob() + dispatcher) }

    private val params: AudioParams = AudioParams()

    override fun setModel(model: String): OpenAiAudioTranscriptions {
        params.model = model
        return this
    }

    override fun setPrompt(prompt: String): OpenAiAudioTranscriptions {
        params.prompt = prompt
        return this
    }

    override fun setResponseFormat(format: String): OpenAiAudioTranscriptions {
        params.responseFormat = format
        return this
    }

    override fun setTemperature(temperature: Double): OpenAiAudioTranscriptions {
        params.temperature = temperature
        return this
    }

    override fun setLanguage(language: String): OpenAiAudioTranscriptions {
        params.language = language
        return this
    }

    override suspend fun execute(filename: String, audioFile: FileBytes): String {
        return audioUseCase.requestAudioTranscription(filename, audioFile, params)
    }

    override fun execute(filename: String, audioFile: FileBytes, callback: OpenAi.Callback<String>) {
        scope.launch {
            runCatching { execute(filename, audioFile) }
                .onSuccess { callback.onSuccess(it) }
                .onFailure { callback.onError(it) }
        }
    }
}
