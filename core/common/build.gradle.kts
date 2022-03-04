plugins {
    id(Libs.plugin.library)
}

common()
navigation()
dagger()

dependencies {
    implementation(*Libs.bundle.kotlinSerialization)
    implementation(*Libs.bundle.firebase)
}