plugins {
    id(Libs.plugin.library)
}

common()
dagger()
lifecycle()

dependencies {
    implementation(projects.coreCommon)
    implementation(projects.coreData)

    implementation(*Libs.bundle.preference)
    implementation(*Libs.bundle.firebase)
}