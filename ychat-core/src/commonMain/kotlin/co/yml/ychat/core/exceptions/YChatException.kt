package co.yml.ychat.core.exceptions

public class YChatException(
    message: String? = null,
    cause: Throwable? = null,
    public var statusCode: Int? = null,
) : Exception(message, cause) {

    public constructor(
        cause: Throwable?,
        statusCode: Int? = null
    ) : this(message = null, cause = cause, statusCode = statusCode)
}
