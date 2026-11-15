plugins {
    java
    alias(libs.plugins.lombok)
    id("buildlogic.java-conventions")
    `maven-publish`
}

version = "1.0-SNAPSHOT"

dependencies {
    compileOnly(libs.org.spongepowered.configurate.core)
    compileOnly(libs.org.spongepowered.configurate.hocon)
    compileOnly(libs.org.spongepowered.configurate.yaml)
    api(libs.co.aikar.acf.core)
    compileOnly(libs.net.kyori.adventure.text.minimessage)
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])

            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()
        }
    }
}