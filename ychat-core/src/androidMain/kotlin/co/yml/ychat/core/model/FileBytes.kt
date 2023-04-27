package co.yml.ychat.core.model

actual typealias FileBytes = ByteArray

actual fun FileBytes.toByteArray(): ByteArray {
    return this
}
