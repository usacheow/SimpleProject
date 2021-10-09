plugins {
    id("library-config")
    id("navigation-config")
    id("dagger-config")
}

dependencies {
    implementation(*Dependencies.Data.Gson.bundle)
}