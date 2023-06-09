plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization").version(libs.versions.kotlin)
    id("io.github.luca992.multiplatform-swiftpackage").version(libs.versions.spmPlugin)
    id("com.vanniktech.maven.publish")
    id("com.android.library")
    id("io.gitlab.arturbosch.detekt")
    id("org.jlleitschuh.gradle.ktlint")
    id("org.jetbrains.kotlinx.kover")
    id("githook-install")
    id("spm-tasks")
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

val iosLibraryName = properties["IOS_NAME"].toString()
version = properties["LIBRARY_VERSION"].toString()

multiplatformSwiftPackage {
    packageName(iosLibraryName)
    swiftToolsVersion("5.3")
    outputDirectory(File(rootDir, "/"))
    targetPlatforms {
        iOS { v("13") }
        macOS { v("11") }
    }
}

kotlin {
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
            baseName = iosLibraryName
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.koin.core)
                implementation(project(":ychat-core"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.mockk.common)
                implementation(libs.ktor.client.mock)
                implementation(libs.koin.test)
            }
        }
        val androidMain by getting
        val androidTest by getting {
            dependencies {
                implementation(libs.mockk)
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
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
        val macosArm64Main by getting
        val macosX64Main by getting
        val macosMain by creating {
            dependsOn(commonMain)
            macosArm64Main.dependsOn(this)
            macosX64Main.dependsOn(this)
        }
        val jvmMain by getting
        val jvmTest by getting {
            dependencies {
                implementation(libs.mockk.jvm)
            }
        }
    }
}

android {
    namespace = "co.yml.ychat"
    compileSdk = libs.versions.config.compile.sdk.version.get().toInt()
    defaultConfig {
        minSdk = libs.versions.config.min.sdk.version.get().toInt()
    }
}

mavenPublishing {
    coordinates("co.yml", "ychat", properties["LIBRARY_VERSION"].toString())
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
