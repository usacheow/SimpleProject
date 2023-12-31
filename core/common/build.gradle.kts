plugins {
    id(Libs.plugin.library)
}

common()
dagger()

android {
    namespace = "com.usacheow.corecommon"
}

dependencies {
    implementation(*Libs.bundle.kotlinSerialization)
    implementation(*Libs.bundle.firebase)
}