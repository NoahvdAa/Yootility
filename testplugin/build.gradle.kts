plugins {
    id("java")
    id("xyz.jpenilla.run-paper") version "1.0.6"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "me.noahvdaa.yootility.testplugin"
version = parent!!.version

repositories {
    mavenCentral()
    maven { url = uri("https://repo.papermc.io/repository/maven-public/") }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.19.2-R0.1-SNAPSHOT")
    implementation(project(":api"))
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

tasks {
    processResources {
        filesMatching("plugin.yml") {
            expand("version" to version)
        }
    }

    runServer {
        minecraftVersion("1.19.2")
    }
}
