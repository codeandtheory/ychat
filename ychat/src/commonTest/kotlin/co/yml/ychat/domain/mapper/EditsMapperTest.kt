package co.yml.ychat.domain.mapper

import co.yml.ychat.data.dto.EditsChoiceDto
import co.yml.ychat.data.dto.EditsDto
import co.yml.ychat.data.dto.UsageDto
import co.yml.ychat.domain.model.EditsParams
import kotlin.test.Test
import kotlin.test.assertEquals

class EditsMapperTest {

    @Test
    fun `on convert EditsDto to EditsModel`() {
        val listOfChoicesDto = listOf(EditsChoiceDto("text 1", 1), EditsChoiceDto("text 2", 2))
        val editsDto = EditsDto(
            created = 12345,
            objectType = "edit",
            choices = listOfChoicesDto,
            usage = UsageDto(1, 1, 1)
        )
        assertEquals(listOfChoicesDto.map { it.text }, editsDto.toEditsModel())
    }

    @Test
    fun `on convert EditsParams to EditsDto`() {
        val editsParams = EditsParams(input = "this is a test")
        assertEquals("text-davinci-edit-001", editsParams.toEditsParamsDto().model)
        assertEquals("this is a test", editsParams.toEditsParamsDto().input)
        assertEquals("", editsParams.toEditsParamsDto().instruction)
        assertEquals(1, editsParams.toEditsParamsDto().results)
        assertEquals(1.0, editsParams.toEditsParamsDto().temperature)
        assertEquals(1.0, editsParams.toEditsParamsDto().topP)
    }
}
