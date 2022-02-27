plugins {
    id("library-config")
    id("navigation-config")
}

dependencies {
    api(projects.core)
    api(*Libs.bundle.uiCore)

    implementation(*Libs.bundle.browser)
}