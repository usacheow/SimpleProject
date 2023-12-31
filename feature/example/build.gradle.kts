plugins {
    id(Libs.plugin.library)
}

common()
compose()
dagger()
lifecycle()

android {
    namespace = "com.usacheow.featureexample"
}

dependencies {
    implementation(projects.coreCommon)
    implementation(projects.coreData)
    implementation(projects.coreUi)
    implementation(projects.coreNavigation)

    implementation(*Libs.bundle.firebase)
}