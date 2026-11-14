plugins {
    `java-library`
}

repositories {
    //mavenLocal() // NEVER use in production builds/push

    mavenCentral()

    maven {
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }

    maven {
        url = uri("https://mvn.alps-bte.com/repository/alps-bte/")
    }
}

group = "de.btegermany.core"

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<Javadoc> {
    options.encoding = "UTF-8"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}