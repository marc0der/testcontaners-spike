plugins {
    id("org.jetbrains.kotlin.jvm") version "1.5.21"
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("org.postgresql:postgresql:42.2.2")

    testImplementation ("io.kotest:kotest-runner-junit5:4.6.1")
    testImplementation ("io.kotest:kotest-assertions-core:4.6.1")

    implementation(platform("org.testcontainers:testcontainers-bom:1.15.3")) {
        testImplementation("org.testcontainers:testcontainers")
        testImplementation("org.testcontainers:postgresql")
        testImplementation("org.testcontainers:junit-jupiter")
    }

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.7.2")
    testImplementation("org.testcontainers:testcontainers:1.15.3")
    testImplementation("org.testcontainers:junit-jupiter:1.15.3")

    testImplementation ("org.testcontainers:postgresql:1.15.3")
}

tasks.withType<Test> {
    useJUnitPlatform()
}