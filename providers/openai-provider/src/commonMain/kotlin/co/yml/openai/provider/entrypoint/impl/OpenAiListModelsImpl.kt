package co.yml.openai.provider.entrypoint.impl

import co.yml.openai.provider.OpenAi
import co.yml.openai.provider.entrypoint.features.OpenAiListModels
import co.yml.openai.provider.domain.model.AIModel
import co.yml.openai.provider.domain.usecases.ListModelsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

internal class OpenAiListModelsImpl(
    private val dispatcher: CoroutineDispatcher,
    private val listModelsUseCase: ListModelsUseCase,
) : OpenAiListModels {

    private val scope by lazy { CoroutineScope(SupervisorJob() + dispatcher) }

    override suspend fun execute(): List<AIModel> {
        return listModelsUseCase.getListModels()
    }

    override fun execute(callback: OpenAi.Callback<List<AIModel>>) {
        scope.launch {
            runCatching { execute() }
                .onSuccess { callback.onSuccess(it) }
                .onFailure { callback.onError(it) }
        }
    }
}
