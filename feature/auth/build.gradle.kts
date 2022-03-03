plugins {
    id("library-config")
    id("navigation-config")
    id("dagger-config")
}

dependencies {
    implementation(projects.coreCommon)
    implementation(projects.coreData)
    implementation(projects.coreUi)
    implementation(projects.coreNavigation)

    implementation(*Libs.bundle.biometric)
    lifecycle()

    testImplementation(projects.coreUnitTest)
    androidTestImplementation(*Libs.bundle.uiTests)
}