package co.yml.ychat.features.impl

import co.yml.ducai.provider.DucAI
import co.yml.ducai.provider.entrypoint.features.DucAICompletions
import co.yml.openai.provider.OpenAI
import co.yml.openai.provider.entrypoint.features.OpenAIChatCompletions
import co.yml.ychat.features.ChatCompletions
import co.yml.ychat.providers.Provider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

internal class ChatCompletionsImpl(
    private val dispatcher: CoroutineDispatcher,
    private val provider: Provider
) : ChatCompletions {

    private val scope by lazy { CoroutineScope(SupervisorJob() + dispatcher) }

    private lateinit var openAiChatCompletions: OpenAIChatCompletions

    private lateinit var ducAiCompletions: DucAICompletions

    init {
        when (provider) {
            is Provider.OpenAi ->
                openAiChatCompletions = OpenAI
                    .create(provider.apiKey)
                    .chatCompletions()
            is Provider.DucAi ->
                ducAiCompletions = DucAI
                    .create()
                    .completion()
        }
    }

    override suspend fun execute(input: String): String {
        return when (provider) {
            is Provider.OpenAi ->
                openAiChatCompletions
                    .execute(input)
                    .first().content
            is Provider.DucAi ->
                ducAiCompletions
                    .setInput(input)
                    .execute()
        }
    }

    override fun execute(input: String, callback: ChatCompletions.Callback<String>) {
        scope.launch {
            runCatching { execute(input) }
                .onSuccess { callback.onSuccess(it) }
                .onFailure { callback.onError(it) }
        }
    }
}
