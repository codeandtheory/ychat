package co.yml.openai.provider.entrypoint.impl

import co.yml.openai.provider.OpenAI
import co.yml.openai.provider.domain.model.ImageGenerationsParams
import co.yml.openai.provider.domain.usecases.ImageGenerationsUseCase
import co.yml.openai.provider.entrypoint.features.OpenAIImageGenerations
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

internal class OpenAIImageGenerationsImpl(
    private val dispatcher: CoroutineDispatcher,
    private val imageGenerationsUseCase: ImageGenerationsUseCase,
) : OpenAIImageGenerations {

    private val scope by lazy { CoroutineScope(SupervisorJob() + dispatcher) }

    private var params: ImageGenerationsParams = ImageGenerationsParams()

    override fun setResults(results: Int): OpenAIImageGenerations {
        params.results = results
        return this
    }

    override fun setSize(size: String): OpenAIImageGenerations {
        params.size = size
        return this
    }

    override fun setResponseFormat(responseFormat: String): OpenAIImageGenerations {
        params.responseFormat = responseFormat
        return this
    }

    override suspend fun execute(prompt: String): List<String> {
        params.prompt = prompt
        return imageGenerationsUseCase.requestImageGenerations(params)
    }

    override fun execute(prompt: String, callback: OpenAI.Callback<List<String>>) {
        scope.launch {
            runCatching { execute(prompt) }
                .onSuccess { callback.onSuccess(it) }
                .onFailure { callback.onError(it) }
        }
    }
}
