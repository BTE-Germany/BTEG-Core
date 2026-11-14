plugins {
    java
    alias(libs.plugins.lombok)
    id("buildlogic.java-conventions")
}

version = "1.0-SNAPSHOT"

dependencies {
    compileOnly(libs.org.spongepowered.configurate.core)
    compileOnly(libs.org.spongepowered.configurate.hocon)
    compileOnly(libs.org.spongepowered.configurate.yaml)
    compileOnly(libs.co.aikar.acf.core)
    compileOnly(libs.net.kyori.adventure.text.minimessage)
}