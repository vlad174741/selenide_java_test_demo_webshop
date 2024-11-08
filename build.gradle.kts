plugins {
    id("java")
    id("io.qameta.allure") version "2.11.2"  // Плагин для Allure
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.slf4j:slf4j-api:2.0.0") // API для SLF4J
    implementation("org.slf4j:slf4j-simple:2.0.0") // Провайдер логирования
    implementation("org.seleniumhq.selenium:selenium-java:4.17.0") // Основная зависимость Selenium
    testImplementation("com.codeborne:selenide:7.1.0") // Selenide для тестирования
    testImplementation(platform("org.junit:junit-bom:5.10.0")) // BOM для JUnit
    testImplementation("org.junit.jupiter:junit-jupiter") // JUnit 5
    testImplementation("io.qameta.allure:allure-selenide:2.20.0") // Интеграция Allure-Selenide
    testImplementation("io.qameta.allure:allure-junit5:2.20.0") // Интеграция Allure с JUnit5
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8" // Кодировка UTF-8 для компиляции Java
}

tasks.test {
    useJUnitPlatform() // Используем платформу JUnit
    systemProperty("file.encoding", "UTF-8") // Кодировка для JVM
    systemProperty("allure.results.directory", "build/allure-results") // Путь для результатов Allure
}