package co.yml.ychat.entrypoint.impl

import co.yml.ychat.YChat
import co.yml.ychat.domain.model.AIModel
import co.yml.ychat.domain.usecases.RetrieveModelUseCase
import co.yml.ychat.entrypoint.features.RetrieveModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

internal class RetrieveModelImpl(
    private val dispatcher: CoroutineDispatcher,
    private val retrieveModelUseCase: RetrieveModelUseCase,
) : RetrieveModel {

    private val scope by lazy { CoroutineScope(SupervisorJob() + dispatcher) }

    override suspend fun execute(id: String): AIModel {
        return retrieveModelUseCase.getModel(id)
    }

    override fun execute(id: String, callback: YChat.Callback<AIModel>) {
        scope.launch {
            runCatching { execute(id) }
                .onSuccess { callback.onSuccess(it) }
                .onFailure { callback.onError(it) }
        }
    }
}
