package co.yml.ychat.domain.model

internal data class EditsParams(
    var model: String = "text-davinci-edit-001",
    var input: String = "",
    var instruction: String = "",
    var results: Int = 1,
    var temperature: Double = 1.0,
    var topP: Double = 1.0,
)
