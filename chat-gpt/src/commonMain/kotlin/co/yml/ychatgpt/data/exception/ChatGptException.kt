package co.yml.ychatgpt.data.exception

class ChatGptException(
    message: String? = null,
    cause: Throwable? = null,
    var statusCode: Int? = null,
) : Exception(message, cause) {

    constructor(
        cause: Throwable?,
        statusCode: Int? = null
    ) : this(message = null, cause = cause, statusCode = statusCode)
}
