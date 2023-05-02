package co.yml.ychat.core.model

public actual typealias FileBytes = ByteArray

public actual fun FileBytes.toByteArray(): ByteArray {
    return this
}
