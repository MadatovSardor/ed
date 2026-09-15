// Ildiz build fayli — bu yerda hech narsa qurilmaydi.
// Plaginlar faqat e'lon qilinadi (`apply false`), har bir modul o'zi kerakligini yoqadi.
// Sabab: har bir modulning bog'liqligi o'z faylida ko'rinib tursin — "sehrli meros" bo'lmasin.

plugins {
    alias(libs.plugins.spring.boot) apply false
    alias(libs.plugins.spring.dep.mgmt) apply false
}

allprojects {
    group = "uz.ed"
    version = "0.1.0"
}