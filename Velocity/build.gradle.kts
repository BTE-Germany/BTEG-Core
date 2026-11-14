plugins {
    java
    alias(libs.plugins.lombok)
    id("buildlogic.java-conventions")
}

version = "1.0-SNAPSHOT"

dependencies {
    compileOnly(project(":core-common"))
    compileOnly(libs.com.velocitypowered.velocity.api)
    annotationProcessor(libs.com.velocitypowered.velocity.api)
    compileOnly(libs.co.aikar.acf.velocity)
}