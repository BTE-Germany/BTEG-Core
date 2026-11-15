plugins {
    java
    alias(libs.plugins.lombok)
    id("buildlogic.java-conventions")
    `maven-publish`
}

version = "1.0-SNAPSHOT"

dependencies {
    compileOnly(project(":core-common"))
    compileOnly(libs.com.velocitypowered.velocity.api)
    annotationProcessor(libs.com.velocitypowered.velocity.api)
    compileOnly(libs.co.aikar.acf.velocity)
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