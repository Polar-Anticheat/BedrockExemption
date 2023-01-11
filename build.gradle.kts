plugins {
    id("java")
}

group = "top.polar"
version = "1.0.0"

repositories {
    maven {
        url = uri("https://repo.opencollab.dev/maven-snapshots/")
    }
    maven {
        url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    }
    maven {
        url = uri("https://jitpack.io")
    }
    mavenCentral()
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.19.3-R0.1-SNAPSHOT")
    compileOnly("org.geysermc.floodgate:api:2.2.0-SNAPSHOT")
    compileOnly("org.spigotmc:plugin-annotations:1.2.3-SNAPSHOT")
    annotationProcessor("org.spigotmc:plugin-annotations:1.2.3-SNAPSHOT")
    compileOnly("com.github.Polar-Anticheat:PolarApi:master-SNAPSHOT")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}