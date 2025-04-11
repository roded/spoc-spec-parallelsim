plugins {
    id("java")
    id("groovy")
}

group = "com.roded"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

dependencies {
    testImplementation("org.gebish:geb-spock:7.0")
    testImplementation("org.seleniumhq.selenium:selenium-remote-driver:4.31.0")
    testImplementation("org.seleniumhq.selenium:selenium-chrome-driver:4.31.0")
    testImplementation("org.seleniumhq.selenium:selenium-api:4.31.0")
    testImplementation("org.seleniumhq.selenium:selenium-java:4.31.0")
//    testImplementation(platform("org.junit:junit-bom:5.10.0"))
//    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
//    maxParallelForks = 1
    maxHeapSize = "4096m"
    jvmArgs(
        "--add-opens", "java.base/java.lang=ALL-UNNAMED",
        "--add-opens", "java.base/java.time=ALL-UNNAMED"
    )
}
