plugins {
    id(Libs.plugin.library)
}

common()
compose()
dagger()

dependencies {
    api(projects.coreUiTheme)
    api(*Libs.bundle.composeTheme)
    api(*Libs.bundle.composeKit)

    implementation(projects.coreCommon)
    implementation(*Libs.bundle.window)
}