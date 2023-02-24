package infrastructure

object MockStorage {

    fun completionSuccessResult(text: String) = "{\"id\":\"1\",\"object\":\"text_completion\"," +
        "\"created\":1675532539,\"model\":\"text-davinci-003\"," +
        "\"choices\":[{\"text\":\"\n\n$text\",\"index\":0,\"logprobs\":null," +
        "\"finish_reason\":\"stop\"}],\"usage\":{\"prompt_tokens\":8,\"completion_tokens\":9," +
        "\"total_tokens\":17}}"
}
