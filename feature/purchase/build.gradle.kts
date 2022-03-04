plugins {
    id(Libs.plugin.library)
}

common()
navigation()
dagger()
lifecycle()

dependencies {
    implementation(projects.coreCommon)
    implementation(projects.coreData)
    implementation(projects.coreUi)
    implementation(projects.coreNavigation)
    implementation(projects.baseBilling)

    implementation(*Libs.bundle.firebase)
}