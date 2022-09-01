plugins {
    id(Libs.plugin.library)
}

common()
compose()
dagger()
lifecycle()

dependencies {
    implementation(projects.coreCommon)
    implementation(projects.coreNavigation)

    implementation(*Libs.bundle.composeWidgetTheme)
    implementation(*Libs.bundle.firebase)
}