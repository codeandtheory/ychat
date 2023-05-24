plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("io.gitlab.arturbosch.detekt")
    id("org.jlleitschuh.gradle.ktlint")
    id("org.jetbrains.kotlinx.kover")
    id("com.vanniktech.maven.publish")
}

kover {
    verify {
        rule {
            name = "Minimal line coverage rate in percents"
            bound {
                minValue = 90
            }
        }
    }
}

kotlin {
    explicitApi()
    android()
    jvm()
    listOf(
        macosArm64(),
        macosX64(),
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "YChatCore"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(Dependencies.Network.KTOR_NEGOTIATION)
                api(Dependencies.Network.KTOR_SERIALIZATION)
                api(Dependencies.Network.KTOR_CORE)
                api(Dependencies.Network.KTOR_LOGGING)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(Dependencies.Test.MOCKK_COMMON)
                implementation(Dependencies.Test.KTOR)
                implementation(Dependencies.Test.KOIN)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Dependencies.Network.KTOR_OKHTTP)
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation(Dependencies.Network.KTOR_IOS)
            }
        }
        val macosArm64Main by getting
        val macosX64Main by getting
        val macosMain by creating {
            dependsOn(commonMain)
            macosArm64Main.dependsOn(this)
            macosX64Main.dependsOn(this)
            dependencies {
                implementation(Dependencies.Network.KTOR_DARWIN)
            }
        }
        val iosTest by creating {
            dependsOn(commonTest)
        }
        val jvmMain by getting {
            dependencies {
                implementation(Dependencies.Network.KTOR_OKHTTP)
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(Dependencies.Test.MOCKK_JVM)
            }
        }
    }
}

android {
    namespace = "co.yml.ychat.core"
    compileSdk = Config.COMPILE_SDK_VERSION
    defaultConfig {
        minSdk = Config.MIN_SDK_VERSION
        targetSdk = Config.TARGET_SDK_VERSION
    }
}

mavenPublishing {
    coordinates("co.yml", "ychat-core", properties["LIBRARY_VERSION"].toString())
    pom {
        developers {
            developer {
                id.set("osugikoji")
                name.set("Koji Osugi")
                url.set("https://github.com/osugikoji")
            }
            developer {
                id.set("renatoarg")
                name.set("Renato Goncalves")
                url.set("https://github.com/renatoarg")
            }
            developer {
                id.set("kikoso")
                name.set("Enrique López Mañas")
                url.set("https://github.com/kikoso")
            }
            developer {
                id.set("samirma")
                name.set("Samir Moreira Antonio")
                url.set("https://github.com/samirma")
            }
        }
    }
}
