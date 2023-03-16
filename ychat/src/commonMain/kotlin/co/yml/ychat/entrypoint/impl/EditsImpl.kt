package co.yml.ychat.entrypoint.impl

import co.yml.ychat.YChat
import co.yml.ychat.domain.model.EditsParams
import co.yml.ychat.domain.usecases.EditsUseCase
import co.yml.ychat.entrypoint.features.Edits
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

internal class EditsImpl(
    private val dispatcher: CoroutineDispatcher,
    private val editsUseCase: EditsUseCase
) : Edits {

    private val scope by lazy { CoroutineScope(SupervisorJob() + dispatcher) }

    private var params: EditsParams = EditsParams()

    override fun setInput(input: String): Edits {
        params.input = input
        return this
    }

    override fun setResults(results: Int): Edits {
        params.results = results
        return this
    }

    override fun setModel(model: String): Edits {
        params.model = model
        return this
    }

    override fun setTemperature(temperature: Double): Edits {
        params.temperature = temperature
        return this
    }

    override fun setTopP(topP: Double): Edits {
        params.topP = topP
        return this
    }

    override suspend fun execute(instruction: String): List<String> {
        params.instruction = instruction
        return editsUseCase.requestEdits(params)
    }

    override fun execute(instruction: String, callback: YChat.Callback<List<String>>) {
        scope.launch {
            runCatching { execute(instruction) }
                .onSuccess { callback.onSuccess(it) }
                .onFailure { callback.onError(it) }
        }
    }
}
