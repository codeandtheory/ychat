plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version(Versions.GRADLE_PLUGIN).apply(false)
    id("com.android.library").version(Versions.GRADLE_PLUGIN).apply(false)
    id("com.vanniktech.maven.publish").version(Versions.PUBLISH_PLUGIN).apply(false)
    id("io.gitlab.arturbosch.detekt").version(Versions.DETEKT_PLUGIN).apply(false)
    id("org.jlleitschuh.gradle.ktlint").version(Versions.KTLINT_PLUGIN).apply(false)
    id("org.jetbrains.kotlinx.kover").version(Versions.KOVER_PLUGIN).apply(false)
    kotlin("android").version(Versions.KOTLIN).apply(false)
    kotlin("multiplatform").version(Versions.KOTLIN).apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
