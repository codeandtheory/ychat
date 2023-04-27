package co.yml.ychat.domain.model

import co.yml.openai.provider.domain.model.ImageGenerationsParams
import kotlin.test.Test
import kotlin.test.assertEquals

class ImageGenerationsParamsTest {

    @Test
    fun `on ImageGenerationsParams verify default values`() {
        // arrange
        val params = ImageGenerationsParams()

        // assert
        assertEquals(true, params.prompt.isEmpty())
        assertEquals(1, params.results)
        assertEquals("256x256", params.size)
        assertEquals("url", params.responseFormat)
        assertEquals("", params.user)
    }
}
