pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenLocal()
        google()
        mavenCentral()
    }
}

rootProject.name = "ychat-sdk"
include(":ychat")
include(":sample:android")
include(":sample:jvm")
include(":ychat-core")
include(":providers:openai-provider")
include(":providers:ducai-provider")
