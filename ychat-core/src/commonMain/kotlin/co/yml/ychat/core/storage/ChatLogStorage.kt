package co.yml.ychat.core.storage

public class ChatLogStorage {

    private val chatLog: MutableList<String> = mutableListOf()

    public fun getChatLog(): String {
        return chatLog.joinToString("\n")
    }

    public fun buildChatInput(input: String): String {
        chatLog.add("Human: $input")
        return getChatLog() + "\n" + "AI: "
    }

    public fun removeLastAppendedInput() {
        chatLog.removeLast()
    }

    public fun appendAnswer(answer: String) {
        chatLog.add("AI: $answer")
    }
}
