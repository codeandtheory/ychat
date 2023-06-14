plugins {
    id("org.springframework.boot").version(libs.versions.spring)
    java
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(project(":ychat"))
    implementation(platform(libs.spring.boot.dependencies))
    implementation(libs.spring.boot.web)
}
