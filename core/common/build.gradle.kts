plugins {
    id(Libs.plugin.library)
}

common()
dagger()

dependencies {
    implementation(*Libs.bundle.kotlinSerialization)
    implementation(*Libs.bundle.firebase)
}