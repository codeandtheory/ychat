object Versions {
    const val GRADLE_PLUGIN = "7.3.0"
    const val DOKKA_PLUGIN = "1.7.20"
    const val DETEKT_PLUGIN = "1.22.0"
    const val KTLINT_PLUGIN = "11.0.0"
    const val KOVER_PLUGIN = "0.6.1"
    const val KOTLIN = "1.7.20"
    const val COMPOSE = "1.3.3"
    const val COMPOSE_FOUNDATION = "1.3.1"
    const val COMPOSE_ACTIVITY = "1.6.1"
    const val COMPOSE_NAVIGATION = "2.5.3"
    const val KTOR = "2.2.2"
    const val KOIN = "3.2.0"
    const val MATERIAL_DESIGN = "1.6.1"
    const val MOCKK = "1.12.3"
}

object Dependencies {

    object Network {
        const val KTOR_NEGOTIATION = "io.ktor:ktor-client-content-negotiation:${Versions.KTOR}"
        const val KTOR_SERIALIZATION = "io.ktor:ktor-serialization-kotlinx-json:${Versions.KTOR}"
        const val KTOR_CORE = "io.ktor:ktor-client-core:${Versions.KTOR}"
        const val KTOR_LOGGING = "io.ktor:ktor-client-logging:${Versions.KTOR}"
        const val KTOR_ANDROID = "io.ktor:ktor-client-android:${Versions.KTOR}"
        const val KTOR_IOS = "io.ktor:ktor-client-ios:${Versions.KTOR}"
    }

    object DI {
        const val KOIN_CORE = "io.insert-koin:koin-core:${Versions.KOIN}"
    }

    object UI {
        const val MATERIAL_DESIGN = "com.google.android.material:${Versions.MATERIAL_DESIGN}"
        const val COMPOSE_UI = "androidx.activity:activity-compose:${Versions.COMPOSE}"
        const val COMPOSE_TOOLING = "androidx.compose.ui:ui-tooling:${Versions.COMPOSE}"
        const val COMPOSE_TOOLING_PREVIEW = "androidx.compose.ui:ui-tooling-preview:${Versions.COMPOSE}"
        const val COMPOSE_FOUNDATION = "androidx.compose.foundation:foundation:${Versions.COMPOSE_FOUNDATION}"
        const val COMPOSE_MATERIAL = "androidx.compose.material:material:${Versions.COMPOSE_FOUNDATION}"
        const val COMPOSE_ACTIVITY = "androidx.activity:activity-compose:${Versions.COMPOSE_ACTIVITY}"
        const val COMPOSE_NAVIGATION = "androidx.navigation:navigation-compose:${Versions.COMPOSE_NAVIGATION}"
    }

    object Test {
        const val MOCKK_COMMON = "io.mockk:mockk-common:${Versions.MOCKK}"
        const val MOCKK = "io.mockk:mockk:${Versions.MOCKK}"
        const val KTOR = "io.ktor:ktor-client-mock:${Versions.KTOR}"
        const val KOIN = "io.insert-koin:koin-test:${Versions.KOIN}"
    }
}
