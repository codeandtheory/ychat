plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

kotlin {
    android()
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "YChatUI"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Dependencies.UI.COMPOSE_UI)
                implementation(Dependencies.UI.COMPOSE_TOOLING)
                implementation(Dependencies.UI.COMPOSE_TOOLING_PREVIEW)
                implementation(Dependencies.UI.COMPOSE_FOUNDATION)
                implementation(Dependencies.UI.COMPOSE_MATERIAL)
                implementation(Dependencies.UI.COMPOSE_RUNTIME)

            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting
        val androidTest by getting
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
    }
}

android {
    namespace = "co.yml.ychatui"
    compileSdk = Config.COMPILE_SDK_VERSION
    defaultConfig {
        minSdk = Config.MIN_SDK_VERSION
        targetSdk = Config.TARGET_SDK_VERSION
    }

    buildFeatures {
        compose = true
    }
}