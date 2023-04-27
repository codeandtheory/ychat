package co.yml.openai.provider.entrypoint.impl

import co.yml.openai.provider.OpenAi
import co.yml.openai.provider.domain.model.ImageGenerationsParams
import co.yml.openai.provider.domain.usecases.ImageGenerationsUseCase
import co.yml.openai.provider.entrypoint.features.OpenAiImageGenerations
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

internal class OpenAiImageGenerationsImpl(
    private val dispatcher: CoroutineDispatcher,
    private val imageGenerationsUseCase: ImageGenerationsUseCase,
) : OpenAiImageGenerations {

    private val scope by lazy { CoroutineScope(SupervisorJob() + dispatcher) }

    private var params: ImageGenerationsParams = ImageGenerationsParams()

    override fun setResults(results: Int): OpenAiImageGenerations {
        params.results = results
        return this
    }

    override fun setSize(size: String): OpenAiImageGenerations {
        params.size = size
        return this
    }

    override fun setResponseFormat(responseFormat: String): OpenAiImageGenerations {
        params.responseFormat = responseFormat
        return this
    }

    override suspend fun execute(prompt: String): List<String> {
        params.prompt = prompt
        return imageGenerationsUseCase.requestImageGenerations(params)
    }

    override fun execute(prompt: String, callback: OpenAi.Callback<List<String>>) {
        scope.launch {
            runCatching { execute(prompt) }
                .onSuccess { callback.onSuccess(it) }
                .onFailure { callback.onError(it) }
        }
    }
}
