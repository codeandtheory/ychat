package infrastructure

object MockStorage {

    fun completionSuccessResult(text: String) = "{\"id\":\"1\",\"object\":\"text_completion\"," +
        "\"created\":1675532539,\"model\":\"text-davinci-003\"," +
        "\"choices\":[{\"text\":\"\n\n$text\",\"index\":0,\"logprobs\":null," +
        "\"finish_reason\":\"stop\"}],\"usage\":{\"prompt_tokens\":8,\"completion_tokens\":9," +
        "\"total_tokens\":17}}"

    fun chatCompletionsSuccessResult(text: String) = "{\"id\":\"1\",\"object\":\"chat.completion\"," +
        "\"created\":1678144798,\"model\":\"gpt-3.5-turbo-0301\"," +
        "\"usage\":{\"prompt_tokens\":13,\"completion_tokens\":12,\"total_tokens\":25}," +
        "\"choices\":[{\"message\":{\"role\":\"assistant\",\"content\":\"$text\"}," +
        "\"finish_reason\":\"stop\",\"index\":0}]}"

    fun imageGenerationsSuccessResult(text: String) =
        "{\"created\":1678805561,\"data\":[{\"url\":\"$text\"}]}"
}
