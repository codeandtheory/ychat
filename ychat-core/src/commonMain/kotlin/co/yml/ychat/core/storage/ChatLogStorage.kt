package co.yml.ychat.core.storage

class ChatLogStorage {

    private val chatLog: MutableList<String> = mutableListOf()

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
