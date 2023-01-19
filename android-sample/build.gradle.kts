plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.chatgpt.sdk.android"
    compileSdk = Config.COMPILE_SDK_VERSION
    defaultConfig {
        applicationId = "com.chatgpt.sdk.android"
        minSdk = Config.MIN_SDK_VERSION
        targetSdk = Config.TARGET_SDK_VERSION
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.0"
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":chat-gpt"))
    implementation(Dependencies.UI.COMPOSE_UI)
    implementation(Dependencies.UI.COMPOSE_TOOLING)
    implementation(Dependencies.UI.COMPOSE_TOOLING_PREVIEW)
    implementation(Dependencies.UI.COMPOSE_FOUNDATION)
    implementation(Dependencies.UI.COMPOSE_MATERIAL)
    implementation(Dependencies.UI.COMPOSE_ACTIVITY)
}
