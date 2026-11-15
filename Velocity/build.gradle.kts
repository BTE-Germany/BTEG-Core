plugins {
    java
    alias(libs.plugins.lombok)
    id("buildlogic.java-conventions")
    `maven-publish`
}

version = "1.0-SNAPSHOT"

dependencies {
    api(project(":core-common"))
    compileOnly(libs.com.velocitypowered.velocity.api)
    annotationProcessor(libs.com.velocitypowered.velocity.api)
    api(libs.co.aikar.acf.velocity)
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