plugins {
    java
    id("com.gradleup.shadow") version "8.3.2"
}

repositories {
    mavenLocal()
    maven("https://jitpack.io")
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://repo.codemc.org/repository/maven-public")
    maven("https://repo.maven.apache.org/maven2/")
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.17.1-R0.1-SNAPSHOT")
    compileOnly("com.github.Slimefun:Slimefun4:experimental-SNAPSHOT")

    implementation("io.github.Mooy1:InfinityLib:1.3.2")
    implementation("com.google.code.findbugs:jsr305:3.0.2")
    compileOnly("org.projectlombok:lombok:1.18.20")
    annotationProcessor("org.projectlombok:lombok:1.18.20")
}

group = "io.github.seggan.slimefunwarfare"
version = "UNOFFICIAL"

java {
    toolchain.languageVersion = JavaLanguageVersion.of(17)
}

tasks.shadowJar {
    archiveClassifier = ""

    relocate("io.github.mooy1.infinitylib", "io.github.seggan.slimefunwarfare.infinitylib")
}
