// Cart: savat faqat Redis'da yashaydi. Narxni catalog.api orqali oladi.
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
    api(project(":modules:shared"))

    // Savat qatorlarini nom va narx bilan boyitish uchun katalog kerak.
    // `implementation`, `api` emas: bu bog'liqlik savatning ICHKI ishi.
    // `api` qilinsa, cart'ga ulangan har bir modul katalogni ham "meros" olib,
    // bilmagan holda unga bog'lanib qolardi.
    implementation(project(":modules:catalog"))

    testImplementation(libs.spring.boot.starter.test)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
    options.compilerArgs.add("-parameters")
}

tasks.withType<Test>().configureEach { useJUnitPlatform() }