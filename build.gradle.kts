plugins {
    java
    application
    checkstyle
    id("com.diffplug.spotless") version "6.25.0"
}

group = "game"
version = "1.0.0"

repositories {
    mavenCentral()
}

val javafxVersion = "21.0.8"
val javafxPlatform = "win"

dependencies {
    implementation("org.openjfx:javafx-controls:$javafxVersion:$javafxPlatform")
    implementation("org.openjfx:javafx-graphics:$javafxVersion:$javafxPlatform")
    implementation("org.openjfx:javafx-base:$javafxVersion:$javafxPlatform")
}

application {
    mainClass.set("game.Main")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.named<JavaExec>("run") {
    jvmArgs = listOf(
        "--module-path", "D:/javafx-sdk-21.0.8/lib",
        "--add-modules", "javafx.controls,javafx.graphics"
    )
}

tasks.jar {
    manifest {
        attributes("Main-Class" to "game.Main")
    }

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    from(
        configurations.runtimeClasspath.get()
            .filter { it.name.endsWith("jar") }
            .map { zipTree(it) }
    )
}

checkstyle {
    toolVersion = "10.17.0"
    configFile = file("config/checkstyle/checks.xml")
}

tasks.withType<Checkstyle> {
    isIgnoreFailures = false
}