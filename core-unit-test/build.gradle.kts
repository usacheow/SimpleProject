plugins {
    id("library-config")
}

dependencies {
    api(projects.core)
    api(*Libs.bundle.unitTests)
}