// Shared kernel: Money, Sku, DomainEvent, outbox, xatolar, security/cache/lock sozlamalari.
// Kichik saqlang — bu yerga faqat BARCHA kontekstlarga tegishli narsa kiradi.
plugins {
    `java-library`
    alias(libs.plugins.spring.dep.mgmt)
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.boot:spring-boot-dependencies:${libs.versions.springBoot.get()}")
    }
}

java {
    toolchain { languageVersion.set(JavaLanguageVersion.of(21)) }
}

dependencies {
    api(libs.spring.boot.starter.webmvc)
    api(libs.spring.boot.starter.validation)
    api(libs.spring.boot.starter.data.jpa)
    api(libs.spring.boot.starter.data.redis)
    api(libs.spring.boot.starter.amqp)
    api(libs.spring.boot.starter.kafka)
    api(libs.spring.boot.starter.security.oauth2.resource.server)
    api(libs.spring.boot.starter.security.oauth2.client)
    api(libs.spring.boot.starter.actuator)
    api(libs.spring.boot.starter.aspectj)
    // jackson-datatype-jsr310 yo'q: Boot 4 dagi Jackson 3 java.time'ni o'zi qo'llaydi.
    api(libs.resilience4j.spring.boot4)
    api(libs.shedlock.spring)
    api(libs.shedlock.jdbc)
    api(libs.micrometer.registry.prometheus)

    testImplementation(libs.spring.boot.starter.test)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
    options.compilerArgs.add("-parameters")
}

tasks.withType<Test>().configureEach { useJUnitPlatform() }
