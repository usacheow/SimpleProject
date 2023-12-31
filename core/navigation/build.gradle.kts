plugins {
    id(Libs.plugin.library)
}

common()
compose()

android {
    namespace = "com.usacheow.corenavigation"
}

dependencies {
    api(*Libs.bundle.navigation)
    api(*Libs.bundle.kotlinSerialization)

    implementation(projects.coreCommon)

    implementation(*Libs.bundle.browser)
}