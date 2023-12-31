plugins {
    id(Libs.plugin.library)
}

common()
compose()
dagger()
lifecycle()

android {
    namespace = "com.usacheow.coreui"
}

dependencies {
    api(projects.coreUiCompose)
    implementation(projects.coreCommon)

    implementation(*Libs.bundle.splashscreen)
    implementation(*Libs.bundle.navigation)
}