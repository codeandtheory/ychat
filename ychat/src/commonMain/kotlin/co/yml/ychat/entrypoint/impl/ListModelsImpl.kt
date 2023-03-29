package co.yml.ychat.entrypoint.impl

import co.yml.ychat.YChat
import co.yml.ychat.domain.model.AIModel
import co.yml.ychat.domain.usecases.ListModelsUseCase
import co.yml.ychat.entrypoint.features.ListModels
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

internal class ListModelsImpl(
    private val dispatcher: CoroutineDispatcher,
    private val listModelsUseCase: ListModelsUseCase,
) : ListModels {

    private val scope by lazy { CoroutineScope(SupervisorJob() + dispatcher) }

    override suspend fun execute(): List<AIModel> {
        return listModelsUseCase.getListModels()
    }

    override fun execute(callback: YChat.Callback<List<AIModel>>) {
        scope.launch {
            runCatching { execute() }
                .onSuccess { callback.onSuccess(it) }
                .onFailure { callback.onError(it) }
        }
    }
}
