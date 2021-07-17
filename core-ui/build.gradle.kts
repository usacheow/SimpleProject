plugins {
    id("library-config")
    id("navigation-config")
    id("glide-config")
    id("dagger-config")
    id("lifecycle-config")
}

dependencies {
    api(*Dependencies.Android.Core.impl)
    api(*Dependencies.Android.Ui.impl)

    implementation(*Dependencies.Android.Biometric.impl)
    implementation(*Dependencies.Android.Browser.impl)
    implementation(*Dependencies.Firebase.impl)
}