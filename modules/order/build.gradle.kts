// Order: Order aggregate va status mashinasi.
// Boshqa modullarning faqat api package'i ishlatiladi (ArchUnit qo'riqlaydi).
// Inventory va Payment bilan to'g'ridan-to'g'ri emas, Kafka event orqali gaplashadi.
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
    // Savat buyurtmaning KIRISH ma'lumoti: PlaceOrderService uni CartApi orqali
    // o'qiydi va buyurtma yaratilgach tozalaydi. Faqat `api` paketiga tegamiz.
    implementation(project(":modules:cart"))
    implementation(project(":modules:catalog"))
    implementation(project(":modules:inventory"))
    implementation(project(":modules:payment"))

    testImplementation(libs.spring.boot.starter.test)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
    options.compilerArgs.add("-parameters")
}

tasks.withType<Test>().configureEach { useJUnitPlatform() }