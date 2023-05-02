package co.yml.openai.provider.entrypoint.impl

import co.yml.openai.provider.OpenAI
import co.yml.openai.provider.entrypoint.features.OpenAIListModels
import co.yml.openai.provider.domain.model.AIModel
import co.yml.openai.provider.domain.usecases.ListModelsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

internal class OpenAIListModelsImpl(
    private val dispatcher: CoroutineDispatcher,
    private val listModelsUseCase: ListModelsUseCase,
) : OpenAIListModels {

    private val scope by lazy { CoroutineScope(SupervisorJob() + dispatcher) }

    override suspend fun execute(): List<AIModel> {
        return listModelsUseCase.getListModels()
    }

    override fun execute(callback: OpenAI.Callback<List<AIModel>>) {
        scope.launch {
            runCatching { execute() }
                .onSuccess { callback.onSuccess(it) }
                .onFailure { callback.onError(it) }
        }
    }
}
