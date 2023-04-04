package co.yml.ychat.domain.model

actual typealias FileBytes = ByteArray

actual fun FileBytes.toByteArray(): ByteArray {
    return this
}
