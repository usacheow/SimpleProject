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

    implementation(*Libs.bundle.biometric)

    testImplementation(projects.coreUnitTest)
    androidTestImplementation(*Libs.bundle.uiTests)
}