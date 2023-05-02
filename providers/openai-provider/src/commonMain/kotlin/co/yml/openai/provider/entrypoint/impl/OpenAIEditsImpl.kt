package co.yml.openai.provider.entrypoint.impl

import co.yml.openai.provider.OpenAI
import co.yml.openai.provider.entrypoint.features.OpenAIEdits
import co.yml.openai.provider.domain.model.EditsParams
import co.yml.openai.provider.domain.usecases.EditsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

internal class OpenAIEditsImpl(
    private val dispatcher: CoroutineDispatcher,
    private val editsUseCase: EditsUseCase
) : OpenAIEdits {

    private val scope by lazy { CoroutineScope(SupervisorJob() + dispatcher) }

    private var params: EditsParams = EditsParams()

    override fun setInput(input: String): OpenAIEdits {
        params.input = input
        return this
    }

    override fun setResults(results: Int): OpenAIEdits {
        params.results = results
        return this
    }

    override fun setModel(model: String): OpenAIEdits {
        params.model = model
        return this
    }

    override fun setTemperature(temperature: Double): OpenAIEdits {
        params.temperature = temperature
        return this
    }

    override fun setTopP(topP: Double): OpenAIEdits {
        params.topP = topP
        return this
    }

    override suspend fun execute(instruction: String): List<String> {
        params.instruction = instruction
        return editsUseCase.requestEdits(params)
    }

    override fun execute(instruction: String, callback: OpenAI.Callback<List<String>>) {
        scope.launch {
            runCatching { execute(instruction) }
                .onSuccess { callback.onSuccess(it) }
                .onFailure { callback.onError(it) }
        }
    }
}
