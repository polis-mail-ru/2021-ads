// Apply the java plugin to add support for Java
plugins {
    java
    application
    `java-library`
    id("me.champeau.jmh") version "0.6.1"
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

repositories {
    jcenter()
}

dependencies {
    // Guava primitives
    implementation("com.google.guava:guava:27.0.1-jre")

    // Annotation
    implementation ("org.jetbrains:annotations:16.0.3")

    // JUnit Jupiter test framework
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.4.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.4.0")

    testImplementation("org.apache.commons:commons-lang3:3.11")
}

val run by tasks.getting(JavaExec::class) {
    standardInput = System.`in`
}

jmh {
}

tasks {
    test {
        maxHeapSize = "128m"
        useJUnitPlatform()
    }
}

application {
    // Define the main class for the application
    mainClassName = "ru.mail.polis.ads.SolveTemplate"
}
