/**
 * Copies a file from the `scripts/pre-push` directory to the `.git/hooks` directory.
 */
if (!File(rootProject.rootDir, ".git/hooks/pre-push").exists()) {
    copy {
        from(File(rootProject.rootDir, "scripts/pre-push"))
        into(File(rootProject.rootDir, ".git/hooks"))
        fileMode = 775
    }
}

/**
 * Creates a Gradle task that makes the files in the `.git/hooks` directory executable.
 */
tasks.create("gitExecutableHooks") {
    Runtime.getRuntime().exec("chmod -R +x .git/hooks/")
}
