plugins {
    id(Libs.plugin.library)
}

common()
compose()
dagger()
lifecycle()
glide()

dependencies {
    api(projects.coreUiCompose)
    implementation(projects.coreCommon)

    implementation(*Libs.bundle.splashscreen)
}