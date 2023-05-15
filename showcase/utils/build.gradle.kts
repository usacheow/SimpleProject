plugins {
    id(Libs.plugin.library)
}

common()
dagger()
lifecycle()

dependencies {
    implementation(projects.coreCommon)
    implementation(projects.coreData)
    implementation(projects.coreUi)

    implementation(projects.baseSources)

    implementation(*Libs.bundle.preference)
    implementation(*Libs.bundle.firebase)
    implementation(*Libs.bundle.appUpdater)
    implementation(*Libs.bundle.biometric)
}