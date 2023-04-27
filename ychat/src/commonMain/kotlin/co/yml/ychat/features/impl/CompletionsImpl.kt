package co.yml.ychat.features.impl

import co.yml.openai.provider.OpenAi
import co.yml.ychat.features.Completions
import co.yml.ychat.providers.Provider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

internal class CompletionsImpl(
    private val dispatcher: CoroutineDispatcher,
    private val provider: Provider
) : Completions {

    private val scope by lazy { CoroutineScope(SupervisorJob() + dispatcher) }

    private var prompt = ""

    override fun setPrompt(prompt: String): Completions {
        this.prompt = prompt
        return this
    }

    override suspend fun execute(): String {
        return when (provider) {
            is Provider.OpenAi ->
                OpenAi
                    .create(provider.apiKey)
                    .completion()
                    .setInput(prompt)
                    .execute()
            is Provider.DucAi ->
                // call DucAi provider
                throw UnsupportedOperationException("Execution is not supported for DucAi provider.")
        }
    }

    override fun execute(callback: Completions.Callback<String>) {
        scope.launch {
            runCatching { execute() }
                .onSuccess { callback.onSuccess(it) }
                .onFailure { callback.onError(it) }
        }
    }
}
