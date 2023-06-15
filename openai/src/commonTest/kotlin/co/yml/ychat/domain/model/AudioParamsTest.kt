package co.yml.ychat.domain.model

import co.yml.openai.provider.domain.model.AudioParams
import kotlin.test.Test
import kotlin.test.assertEquals

class AudioParamsTest {

    @Test
    fun `on AudioParams verify default values`() {
        // arrange
        val params = AudioParams()

        // assert
        assertEquals("whisper-1", params.model)
        assertEquals("", params.prompt)
        assertEquals("json", params.responseFormat)
        assertEquals(0.0, params.temperature)
        assertEquals("en", params.language)
    }
}
