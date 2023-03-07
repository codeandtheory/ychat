package co.yml.ychat.domain.model

import kotlin.test.Test
import kotlin.test.assertEquals

class CompletionParamsTest {

    @Test
    fun `on completionParams verify default values`() {
        // arrange
        val params = CompletionParams()

        // assert
        assertEquals("", params.prompt)
        assertEquals("text-davinci-003", params.model)
        assertEquals(1024, params.maxTokens)
        assertEquals(1.0, params.temperature)
        assertEquals(1.0, params.topP)
        assertEquals(false, params.enableChatStorage)
    }
}
