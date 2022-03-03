plugins {
    id("library-config")
}

dependencies {
    api(*Libs.bundle.unitTests)

    implementation(projects.coreCommon)
}