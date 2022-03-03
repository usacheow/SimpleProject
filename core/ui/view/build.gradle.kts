plugins {
    id("library-config")
    id("dagger-config")
}

dependencies {
    api(projects.coreUiTheme)
    api(*Libs.bundle.viewTheme)
    api(*Libs.bundle.viewKit)

    implementation(projects.coreCommon)

    implementation(*Libs.bundle.splashscreen)
    lifecycle()
    glide()
}