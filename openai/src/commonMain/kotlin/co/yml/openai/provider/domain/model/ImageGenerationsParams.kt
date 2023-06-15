package co.yml.openai.provider.domain.model

/**
 * Parameters to configure the Image Generations API.
 *
 * @param prompt The prompt(s) to generate images for.
 *
 * @param results: Quantity of images to be generated.
 *
 * @param size: The size of the images generated (squared). Ex. 256x256, 512x512, 1024x1024
 *
 * @param responseFormat: The format in which the generated images are returned. Must be one of url or b64_json.
 *
 * @param user: A unique identifier representing your end-user, which can help OpenAI to monitor and detect abuse.
 */
internal data class ImageGenerationsParams(
    var prompt: String = "",
    var results: Int = 1,
    var size: String = "256x256",
    var responseFormat: String = "url",
    var user: String = "",
)
