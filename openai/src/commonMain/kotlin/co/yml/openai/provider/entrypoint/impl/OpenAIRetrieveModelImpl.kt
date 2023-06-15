package co.yml.openai.provider.entrypoint.impl

import co.yml.openai.provider.OpenAI
import co.yml.openai.provider.entrypoint.features.OpenAIRetrieveModel
import co.yml.openai.provider.domain.model.AIModel
import co.yml.openai.provider.domain.usecases.RetrieveModelUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

internal class OpenAIRetrieveModelImpl(
    private val dispatcher: CoroutineDispatcher,
    private val retrieveModelUseCase: RetrieveModelUseCase,
) : OpenAIRetrieveModel {

    private val scope by lazy { CoroutineScope(SupervisorJob() + dispatcher) }

    override suspend fun execute(id: String): AIModel {
        return retrieveModelUseCase.getModel(id)
    }

    override fun execute(id: String, callback: OpenAI.Callback<AIModel>) {
        scope.launch {
            runCatching { execute(id) }
                .onSuccess { callback.onSuccess(it) }
                .onFailure { callback.onError(it) }
        }
    }
}
