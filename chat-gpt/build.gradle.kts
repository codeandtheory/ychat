plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("maven-publish")
    id("signing")
    id("org.jetbrains.dokka") version "1.7.20"
}

kotlin {
    android()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "chat-gpt"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Dependencies.Network.KTOR_NEGOTIATION)
                implementation(Dependencies.Network.KTOR_SERIALIZATION)
                implementation(Dependencies.Network.KTOR_CORE)
                implementation(Dependencies.Network.KTOR_LOGGING)
                implementation(Dependencies.DI.KOIN_CORE)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Dependencies.Network.KTOR_ANDROID)
            }
        }
        val androidTest by getting
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
    namespace = "co.yml.chatgpt"
    compileSdk = Config.COMPILE_SDK_VERSION
    defaultConfig {
        minSdk = Config.MIN_SDK_VERSION
        targetSdk = Config.TARGET_SDK_VERSION
    }
}

val dokkaOutputDir = "$buildDir/dokka"

tasks.dokkaHtml {
    outputDirectory.set(file(dokkaOutputDir))
}

val deleteDokkaOutputDir by tasks.register<Delete>("deleteDokkaOutputDirectory") {
    delete(dokkaOutputDir)
}
val javadocJar = tasks.register<Jar>("javadocJar") {
    dependsOn(deleteDokkaOutputDir, tasks.dokkaHtml)
    archiveClassifier.set("javadoc")
    from(dokkaOutputDir)
}

publishing {
    repositories {
        maven {
            name = "YChatGPT"
            setUrl("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2")
            credentials {
                username = project.findProperty("mavenCentralUsername")?.toString() ?: System.getenv("MAVEN_USERNAME")
                password = project.findProperty("mavenCentralPassword")?.toString() ?: System.getenv("MAVEN_PASSWORD")
            }
        }
    }
    publications {
        register<MavenPublication>("release") {
            groupId = "co.yml"
            artifactId = "ychatgpt"
            version = "0.0.1"
            afterEvaluate {
                from(components["release"])
            }
            artifact(javadocJar)
            pom {
                name.set("YChatGPT")
                description.set("YChatGPT SDK is kotlin multiplatform library for chat gpt apis.")
                url.set("https://github.com/yml-org/chatgpt-sdk")
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                        distribution.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
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
                }
                scm {
                    url.set("https://github.com/yml-org/chatgpt-sdk")
                    connection.set("scm:git:git://github.com/yml-org/chatgpt-sdk.git")
                    developerConnection.set("scm:git:ssh://git@github.com:yml-org/chatgpt-sdk.git")
                }
            }
        }
    }
}


signing {
    useInMemoryPgpKeys(
        project.findProperty("signing.keyId")?.toString() ?: System.getenv("SIGNINGKEY"),
        project.findProperty("signing.InMemoryKey")?.toString() ?: System.getenv("MEMORY_KEY"),
        project.findProperty("signing.password")?.toString()?:System.getenv("SIGNINGPASSWORD")
    )
    sign(publishing.publications)
}
