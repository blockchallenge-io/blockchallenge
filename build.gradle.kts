import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    java
    id("com.github.johnrengelman.shadow") version "6.0.0"
}

group = "io.blockchallenge"
version = "1.0"

repositories {
    mavenCentral()
    maven {
        name = "spigot-repo"
        setUrl("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    }
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.17.1-R0.1-SNAPSHOT")

    implementation(project(":blockchallenge-base"))
    implementation(project(":blockchallenge-actions"))
    implementation(project(":blockchallenge-item"))
    implementation(project(":blockchallenge-world"))
}

tasks {
    named<ShadowJar>("shadowJar") {
        dependencies { include(project(":blockchallenge-base")) }
        dependencies { include(project(":blockchallenge-actions")) }
        dependencies { include(project(":blockchallenge-item")) }
        dependencies { include(project(":blockchallenge-world")) }
    }

    processResources {
        expand("version" to project.version)
    }

    build { dependsOn(shadowJar) }
}