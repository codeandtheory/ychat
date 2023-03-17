package co.yml.ychat.domain.mapper

import co.yml.ychat.data.dto.ImageGenerationsDto
import co.yml.ychat.data.dto.ImageGeneratedDto
import co.yml.ychat.domain.model.ImageGenerationsParams
import kotlin.test.Test
import kotlin.test.assertEquals

class ImageGenerationsMapperTest {

    @Test
    fun `on convert ImageGenerationsDto to ImageGenerated`() {
        val listOfImageGeneratedDto = listOf(ImageGeneratedDto("http://url1.test"), ImageGeneratedDto("http://url2.test"))
        val imageGenerationsDto = ImageGenerationsDto(
            created = 12345,
            data = listOfImageGeneratedDto
        )
        assertEquals(listOfImageGeneratedDto.map { it.url }, imageGenerationsDto.toImageGenerated())
    }

    @Test
    fun `on convert ImageGenerationsParams to ImageGenerationsDto`() {
        val imageGenerationsParams = ImageGenerationsParams(prompt = "/image test")
        assertEquals("/image test", imageGenerationsParams.toImageGenerationsParamsDto().prompt)
        assertEquals("url", imageGenerationsParams.toImageGenerationsParamsDto().responseFormat)
        assertEquals("256x256", imageGenerationsParams.toImageGenerationsParamsDto().size)
        assertEquals("", imageGenerationsParams.toImageGenerationsParamsDto().user)
        assertEquals(1, imageGenerationsParams.toImageGenerationsParamsDto().results)
    }
}
