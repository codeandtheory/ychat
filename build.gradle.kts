plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application") version libs.versions.gradlePlugin apply false
    id("com.android.library").version(libs.versions.gradlePlugin).apply(false)
    id("com.vanniktech.maven.publish").version(libs.versions.publishPlugin).apply(false)
    id("io.gitlab.arturbosch.detekt").version(libs.versions.detektPlugin).apply(false)
    id("org.jlleitschuh.gradle.ktlint").version(libs.versions.ktlintPlugin).apply(false)
    id("org.jetbrains.kotlinx.kover").version(libs.versions.koverPlugin).apply(false)
    kotlin("android").version(libs.versions.kotlin).apply(false)
    kotlin("multiplatform").version(libs.versions.kotlin).apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
