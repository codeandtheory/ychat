import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "co.yml.ychat.android"
    compileSdk = libs.versions.config.compile.sdk.version.get().toInt()
    defaultConfig {
        applicationId = "co.yml.ychat.android"
        minSdk = libs.versions.config.min.sdk.version.get().toInt()
        targetSdk = libs.versions.config.target.sdk.version.get().toInt()
        versionCode = 1
        versionName = "1.0"

        val key = "api.key"
        val apiKey = System
            .getenv()
            .getOrDefault(key, gradleLocalProperties(rootDir).getProperty(key))
        buildConfigField("String", "API_KEY", "\"$apiKey\"")
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }

    packaging {
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
    implementation(project(":ychat"))
    implementation(libs.androidx.compose.tooling)
    implementation(libs.androidx.compose.tooling.preview)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.activity)
    implementation(libs.androidx.compose.navigation)
    implementation(libs.androidx.compose.livedata)
    implementation(libs.coil)
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.compose)
}
