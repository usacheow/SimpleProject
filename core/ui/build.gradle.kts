plugins {
    id(Libs.plugin.library)
}

common()
dagger()
lifecycle()
glide()

dependencies {
    api(projects.coreUiCompose)
    api(projects.coreUiView)

    implementation(projects.coreCommon)

    implementation(*Libs.bundle.splashscreen)
}