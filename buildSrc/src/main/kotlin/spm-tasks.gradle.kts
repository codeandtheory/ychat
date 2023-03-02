import java.io.File
import org.gradle.api.GradleException

tasks.register("checkSwiftPackageVersion") {
    group = "Multiplatform-swift-package"
    description = "Checks if the Swift package has the updated version by comparing it with the current library version."
    doLast {
        val iosLibraryName = properties["IOS_NAME"]
        val libraryVersion = properties["VERSION_NAME"]
        val iosPackage = "$iosLibraryName-$libraryVersion.zip"
        val filePath = File(rootProject.rootDir, iosPackage)
        if (!filePath.exists()) {
            throw GradleException("$iosPackage not found. Please, update the swift package.")
        }
        println("$iosPackage is updated")
    }
}

tasks.register<Delete>("updateSwiftPackage") {
    group = "Multiplatform-swift-package"
    description = "Updates the Swift package by removing outdated files and running the `createSwiftPackage` task."
    doFirst {
        rootDir.listFiles { file ->
            val iosLibraryName = properties["library.ios.name"]
            val fileName = file.name
            if (fileName.startsWith("$iosLibraryName-") && fileName.endsWith(".zip")) {
                delete(file)
                println("Removed $fileName file.")
            }
            false
        }
    }
    finalizedBy("createSwiftPackage")
}
