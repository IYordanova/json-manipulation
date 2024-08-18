plugins {
    kotlin("jvm") version "2.0.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.2")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.17.2")

    implementation("com.hubspot.jinjava:jinjava:2.7.2")

    implementation("com.schibsted.spt.data:jslt:0.1.14")

    implementation("org.apache.velocity:velocity:1.7")
    implementation("org.apache.velocity:velocity-tools:2.0")

    implementation("org.freemarker:freemarker:2.3.33")
    implementation("org.freemarker:freemarker-gae:2.3.33")

    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}