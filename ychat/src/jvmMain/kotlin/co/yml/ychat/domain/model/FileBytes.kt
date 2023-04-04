package co.yml.ychat.domain.model

actual typealias FileBytes = ByteArray

internal actual fun FileBytes.toByteArray(): ByteArray {
    return this
}
