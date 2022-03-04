plugins {
    id(Libs.plugin.library)
}

common()
compose()
dagger()

dependencies {
    implementation(projects.coreCommon)

    implementation(*Libs.bundle.splashscreen)
    implementation(*Libs.bundle.viewTheme)
    implementation(*Libs.bundle.composeTheme)
}