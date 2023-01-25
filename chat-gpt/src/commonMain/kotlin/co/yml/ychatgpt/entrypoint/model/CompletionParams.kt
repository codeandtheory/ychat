package co.yml.ychatgpt.entrypoint.model

/**
 * Parameters to configure the chat GPT completion API.
 *
 * [model]: ID of the model to use.
 *
 * [maxTokens]: The maximum number of tokens to generate in the completion. The token count of your
 * prompt plus max_tokens cannot exceed the model's context length. Most models have a context
 * length of 2048 tokens (except for the newest models, which support 4096).
 *
 * [temperature]: What sampling temperature to use. Higher values means the model will take more
 * risks. Try 0.9 for more creative applications, and 0 (argmax sampling) for ones with a
 * well-defined answer. We generally recommend altering this or [topP] but not both.
 *
 * [topP]: An alternative to sampling with temperature, called nucleus sampling, where the model
 * considers the results of the tokens with top_p probability mass. So 0.1 means only the tokens
 * comprising the top 10% probability mass are considered. We generally recommend altering this or
 * [temperature] but not both.
 *
 * [enableChatStorage] This flag enables the chat history to be stored, so that the GPT chat can
 * get the context of previous conversations.
 */
data class CompletionParams(
    var model: String = "text-davinci-003",
    var maxTokens: Int = 150,
    var temperature: Double = 1.0,
    var topP: Double = 1.0,
    var enableChatStorage: Boolean = false,
)
