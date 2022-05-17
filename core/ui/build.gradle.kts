plugins {
    id(Libs.plugin.library)
}

common()
compose()
dagger()
lifecycle()

dependencies {
    api(projects.coreUiCompose)
    implementation(projects.coreCommon)

    implementation(*Libs.bundle.splashscreen)
    implementation(*Libs.bundle.biometric)
}