plugins {
    id("library-config")
    id("navigation-config")
    id("lifecycle-config")
    id("glide-config")
    id("dagger-config")
}

dependencies {
    api(project(Modules.CORE.path))
    api(*Dependencies.Android.Ui.bundle)

    implementation(*Dependencies.Android.Biometric.bundle)
    implementation(*Dependencies.Android.Browser.bundle)
    implementation(*Dependencies.Firebase.bundle)
}