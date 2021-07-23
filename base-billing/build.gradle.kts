plugins {
    id("library-config")
    id("dagger-config")
    id("lifecycle-config")
}

dependencies {
    api(*Dependencies.Android.Billing.impl)

    implementation(project(Modules.CORE_DATA.path))
    implementation(project(Modules.CORE_UI.path))

    implementation(*Dependencies.Firebase.impl)
}