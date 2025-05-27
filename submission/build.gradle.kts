plugins {
    id("com.android.security.autorepro.submission")
    id("org.jetbrains.kotlin.android") version "1.9.23" apply false
}

dependencies {
    // Automatically add each subproject as an AutoRepro Test Resource
    fileTree(projectDir) { include("*/build.gradle*") }
        .map { projectDir.toPath().relativize(it.toPath().parent).toString().replace('/', ':') }
        .filter(String::isNotEmpty)
        .map(::project)
        .forEach(::testResource)
}

submission {
    // Please configure your submission attributes here
}
