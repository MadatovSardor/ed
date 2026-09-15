// Spring Boot ilovasi: faqat shu yerda main() va bootJar.
plugins {
    java
    alias(libs.plugins.spring.boot)
    // spring-boot plagini bilan birga qo'llansa, Spring Boot BOM avtomatik import qilinadi.
    // Testcontainers versiyasi ham shu BOM ichida — alohida testcontainers-bom kerak emas.
    alias(libs.plugins.spring.dep.mgmt)
}

java {
    toolchain { languageVersion.set(JavaLanguageVersion.of(21)) }
}

dependencies {
    implementation(project(":modules:shared"))
    implementation(project(":modules:catalog"))
    implementation(project(":modules:cart"))
    implementation(project(":modules:order"))
    implementation(project(":modules:payment"))
    implementation(project(":modules:inventory"))
    implementation(project(":modules:notification"))
    implementation(project(":modules:identity"))

    // Boot 4: auto-configuration alohida modullarga ko'chgan — yalang'och flyway-core
    // yoki micrometer-tracing-bridge-otel qo'shilsa, ular avtomatik SOZLANMAYDI.
    // Starter kerak.
    implementation(libs.spring.boot.starter.flyway)
    implementation(libs.flyway.postgres)
    implementation(libs.micrometer.registry.prometheus)
    // micrometer-tracing-bridge-otel + opentelemetry-exporter-otlp shu starter ichida.
    implementation(libs.spring.boot.starter.opentelemetry)
    implementation(libs.logstash.encoder)

    runtimeOnly(libs.postgresql)

    testImplementation(libs.spring.boot.starter.test)
    // @ServiceConnection shu modulda: konteyner porti/paroli
    // avtomatik ravishda Spring property'lariga bog'lanadi.
    // Versiya Spring Boot BOM dan keladi — shuning uchun bu yerda ko'rsatilmagan.
    testImplementation(libs.spring.boot.testcontainers)
    testImplementation(libs.spring.boot.starter.security.test)
    testImplementation(libs.spring.boot.starter.kafka.test)
    testImplementation(libs.spring.boot.starter.amqp.test)
    testImplementation(libs.testcontainers.junit)
    testImplementation(libs.testcontainers.postgres)
    testImplementation(libs.testcontainers.kafka)
    testImplementation(libs.testcontainers.rabbit)
    testImplementation(libs.testcontainers.keycloak)
    testImplementation(libs.archunit.junit5)
    testImplementation(libs.awaitility)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
    options.compilerArgs.add("-parameters")
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
    testLogging { events("passed", "skipped", "failed") }
}
