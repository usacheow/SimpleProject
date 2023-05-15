plugins {
    id(Libs.plugin.library)
}

common()
dagger()
lifecycle()

dependencies {
    implementation(*Libs.bundle.billing)

    implementation(projects.coreCommon)
    implementation(projects.coreData)
    implementation(projects.coreUi)

    implementation(*Libs.bundle.firebase)
}