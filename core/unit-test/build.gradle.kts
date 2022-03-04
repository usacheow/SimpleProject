plugins {
    id(Libs.plugin.library)
}

common()

dependencies {
    api(*Libs.bundle.unitTests)

    implementation(projects.coreCommon)
}