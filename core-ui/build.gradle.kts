plugins {
    id("library-config")
    id("navigation-config")
    id("glide-config")
    id("dagger-config")
}

dependencies {
    api(*Dependencies.Android.Ui.impl)
    api(*Dependencies.Android.Lifecycle.impl)
    kapt(Dependencies.Android.Lifecycle.kapt)

    implementation(*Dependencies.Android.Biometric.impl)
    implementation(*Dependencies.Android.Browser.impl)
    implementation(*Dependencies.Firebase.impl)
}