plugins {
    id("java")
    id("application")
}

group = "game"
version = "1.0"

repositories {
    mavenCentral()
}

application {
    mainClass = "game.Main"
}

dependencies {
    implementation("org.openjfx:javafx-controls:21.0.8:win")
    implementation("org.openjfx:javafx-graphics:21.0.8:win")
    implementation("org.openjfx:javafx-base:21.0.8:win")
}

tasks.named<JavaExec>("run") {
    jvmArgs = listOf(
        "--module-path", "D:javafx-sdk-21.0.8/lib",
        "--add-modules", "javafx.controls,javafx.graphics"
    )
}

