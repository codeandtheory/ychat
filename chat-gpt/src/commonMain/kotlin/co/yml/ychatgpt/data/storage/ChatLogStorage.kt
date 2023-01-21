package co.yml.ychatgpt.data.storage

internal class ChatLogStorage {

    private val chatLog = mutableListOf(
        "Human: Hello, how are you?",
        "AI: I am doing great. How can I help you today?"
    )

    fun getChatLog(): String {
        return chatLog.joinToString("\n")
    }

    fun buildChatInput(input: String): String {
        chatLog.add("Human: $input")
        return getChatLog() + "\n" + "AI: "
    }

    fun removeLastAppendedInput() {
        chatLog.removeLast()
    }

    fun appendAnswer(answer: String) {
        chatLog.add("AI: $answer")
    }
}