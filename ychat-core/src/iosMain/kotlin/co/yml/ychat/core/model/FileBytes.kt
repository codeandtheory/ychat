package co.yml.ychat.core.model

import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned
import platform.Foundation.NSData
import platform.posix.memcpy

actual typealias FileBytes = NSData

actual fun FileBytes.toByteArray(): ByteArray {
    return ByteArray(this.length.toInt()).apply {
        usePinned {
            memcpy(it.addressOf(0), this@toByteArray.bytes, this@toByteArray.length)
        }
    }
}
