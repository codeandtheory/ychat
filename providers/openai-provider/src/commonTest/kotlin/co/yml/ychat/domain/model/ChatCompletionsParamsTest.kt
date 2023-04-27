package co.yml.ychat.domain.model

import co.yml.openai.provider.domain.model.ChatCompletionsParams
import kotlin.test.Test
import kotlin.test.assertEquals

class ChatCompletionsParamsTest {

    @Test
    fun `on ChatCompletionsParams verify default values`() {
        // arrange
        val params = ChatCompletionsParams()

        // assert
        assertEquals(true, params.messages.isEmpty())
        assertEquals("gpt-3.5-turbo", params.model)
        assertEquals(4096, params.maxTokens)
        assertEquals(1, params.maxResults)
        assertEquals(1.0, params.temperature)
        assertEquals(1.0, params.topP)
    }
}
