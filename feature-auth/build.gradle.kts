plugins {
    id("library-config")
    id("navigation-config")
    id("lifecycle-config")
    id("dagger-config")
}

dependencies {
    implementation(projects.coreData)
    implementation(projects.coreUi)
    implementation(projects.coreMediator)

    implementation(projects.baseOtp)

    implementation(*Libs.bundle.biometric)

    testImplementation(projects.coreUnitTest)
    androidTestImplementation(*Libs.bundle.uiTests)
}