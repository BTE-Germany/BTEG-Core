plugins {
    java
    alias(libs.plugins.lombok)
    id("buildlogic.java-conventions")
    `maven-publish`
}

version = "1.0-SNAPSHOT"

dependencies {
    compileOnly(project(":core-common"))
    compileOnly(libs.io.papermc.paper.paper.api)
    compileOnly(libs.dev.triumphteam.triumph.gui) {
        exclude (group = "org.jetbrains", module = "annotations")
        exclude (group = "net.kyori", module = "adventure-api")
        exclude (group = "net.kyori", module = "adventure-platform-bukkit")
        exclude (group = "net.kyori", module = "adventure-text-serializer-gson")
        exclude (group = "net.kyori", module = "adventure-text-serializer-legacy")
    }
    compileOnly(libs.org.json.json)
    compileOnly(libs.co.aikar.acf.paper)
    compileOnly(libs.li.cinnazeyy.langlibs.api)
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