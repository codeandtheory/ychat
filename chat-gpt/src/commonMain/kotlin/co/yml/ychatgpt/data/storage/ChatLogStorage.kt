package co.yml.ychatgpt.data.storage

internal class ChatLogStorage {

    private val chatLog = arrayListOf(
        "Human: Hello, how are you?",
        "AI: I am doing great. How can I help you today?"
    )

    fun getChatLog(question: String): String {
        chatLog.add("Human: $question")
        return chatLog.joinToString("\n")
    }

    fun appendAnswer(answer: String) {
        chatLog.add(answer.trimStart())
    }
}