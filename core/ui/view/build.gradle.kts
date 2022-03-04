plugins {
    id(Libs.plugin.library)
}

common()
dagger()
lifecycle()
glide()

dependencies {
    api(projects.coreUiTheme)
    api(*Libs.bundle.viewTheme)
    api(*Libs.bundle.viewKit)

    implementation(projects.coreCommon)

    implementation(*Libs.bundle.splashscreen)
}