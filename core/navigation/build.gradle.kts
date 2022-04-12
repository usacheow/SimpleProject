plugins {
    id(Libs.plugin.library)
}

common()
compose()

dependencies {
    api(*Libs.bundle.navigation)
    api(*Libs.bundle.kotlinSerialization)

    implementation(projects.coreCommon)

    implementation(*Libs.bundle.browser)
}