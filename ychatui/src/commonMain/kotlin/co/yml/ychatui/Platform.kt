package co.yml.ychatui

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform