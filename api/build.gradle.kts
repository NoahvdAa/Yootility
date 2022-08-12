plugins {
    id("java")
    id("maven-publish")
}

group = parent!!.group
version = parent!!.version

repositories {
    mavenCentral()
    maven { url = uri("https://repo.papermc.io/repository/maven-public/") }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.19.2-R0.1-SNAPSHOT")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))

    withJavadocJar()
    withSourcesJar()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            artifactId = "yootility"

            from(components["java"])
        }
    }

    repositories {
        maven {
            name = "bytecodespace"

            val releasesRepoUrl = uri("https://repo.bytecode.space/repository/maven-releases/")
            val snapshotsRepoUrl = uri("https://repo.bytecode.space/repository/maven-snapshots/")
            url = if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl

            credentials(PasswordCredentials::class)
        }
    }
}
