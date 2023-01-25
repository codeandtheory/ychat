package co.yml.ychatgpt.data.storage

import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ChatLogStorageTest {

    private lateinit var chatLogStorage: ChatLogStorage

    @BeforeTest
    fun setup() {
        chatLogStorage = ChatLogStorage()
    }

    @Test
    fun `on buildChatInput then should return chat log with appended input`() {
        // arrange
        val input = "Say this is a test"

        // act
        val result = chatLogStorage.buildChatInput(input)

        // assert
        assertEquals(
            expected = "Human: Say this is a test" + "\n" +
                "AI: ",
            actual = result
        )
    }

    @Test
    fun `on removeLastAppendedInput then should remove last appended input`() {
        // arrange
        val input = "Say this is a test"
        val answer = "This in indeed a test!"

        // act
        chatLogStorage.buildChatInput(input)
        chatLogStorage.appendAnswer(answer)
        chatLogStorage.removeLastAppendedInput()
        val result = chatLogStorage.getChatLog()

        // assert
        assertEquals(
            expected = "Human: Say this is a test",
            actual = result
        )
    }

    @Test
    fun `on appendAnswer then should append answer in log chat`() {
        // arrange
        val input = "Say this is a test"

        // act
        chatLogStorage.buildChatInput(input)
        chatLogStorage.appendAnswer("This is indeed a test")
        val result = chatLogStorage.getChatLog()

        // assert
        assertEquals(
            expected = "Human: Say this is a test" + "\n" +
                "AI: This is indeed a test",
            actual = result
        )
    }
}
