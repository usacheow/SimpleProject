plugins {
    id("library-config")
    id("navigation-config")
}

dependencies {
    implementation(projects.coreCommon)

    implementation(*Libs.bundle.browser)
}