plugins {
    id("library-config")
    id("navigation-config")
    id("lifecycle-config")
    id("glide-config")
    id("dagger-config")
}

dependencies {
    api(*Dependencies.Android.Ui.impl)

    implementation(*Dependencies.Android.Biometric.impl)
    implementation(*Dependencies.Android.Browser.impl)
    implementation(*Dependencies.Firebase.impl)
}