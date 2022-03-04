plugins {
    id(Libs.plugin.library)
}

common()
navigation()

dependencies {
    implementation(projects.coreCommon)

    implementation(*Libs.bundle.browser)
}