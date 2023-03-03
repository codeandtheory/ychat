plugins {
    id("org.springframework.boot").version(Versions.SPRING)
    java
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(project(":ychat"))
    implementation(platform(Dependencies.Spring.SPRING_DEP))
    implementation(Dependencies.Spring.SPRING_WEB)
}
