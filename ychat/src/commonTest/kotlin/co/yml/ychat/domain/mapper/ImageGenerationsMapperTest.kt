package co.yml.ychat.domain.mapper

import co.yml.ychat.data.dto.ImageGenerationsDto
import co.yml.ychat.domain.model.ImageGenerated
import co.yml.ychat.domain.model.ImageGenerationsParams
import kotlin.test.Test
import kotlin.test.assertEquals

class ImageGenerationsMapperTest {

    @Test
    fun `on convert ImageGenerationsDto to ImageGenerated`() {
        val listOfImageGenerated = listOf(ImageGenerated("http://url1.test"), ImageGenerated("http://url2.test"))
        val imageGenerationsDto = ImageGenerationsDto(
            created = 12345,
            data = listOfImageGenerated
        )
        assertEquals(listOfImageGenerated, imageGenerationsDto.toImageGenerated())
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
