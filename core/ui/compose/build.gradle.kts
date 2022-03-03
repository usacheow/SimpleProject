plugins {
    id("library-config")
    id("dagger-config")
}

dependencies {
    api(projects.coreUiTheme)
    api(*Libs.bundle.composeTheme)
    api(*Libs.bundle.composeKit)

    implementation(projects.coreCommon)
}