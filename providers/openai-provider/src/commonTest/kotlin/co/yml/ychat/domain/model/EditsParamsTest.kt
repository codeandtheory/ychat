package co.yml.ychat.domain.model

import co.yml.openai.provider.domain.model.EditsParams
import kotlin.test.Test
import kotlin.test.assertEquals

class EditsParamsTest {

    @Test
    fun `on EditsParams verify default values`() {
        // arrange
        val params = EditsParams()

        // assert
        assertEquals("text-davinci-edit-001", params.model)
        assertEquals("", params.input)
        assertEquals("", params.instruction)
        assertEquals(1, params.results)
        assertEquals(1.0, params.temperature)
        assertEquals(1.0, params.topP)
    }
}
