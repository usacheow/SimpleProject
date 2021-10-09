plugins {
    id("library-config")
    id("dagger-config")
    id("room-config")
}

android {
    defaultConfig {
        buildConfigField("String", "ENDPOINT", "\"https://stub.com/api/v1/\"")
    }
}

dependencies {
    api(project(Modules.CORE.path))
    api(*Dependencies.Data.Requests.bundle)
    api(*Dependencies.Data.Gson.bundle)

    implementation(*Dependencies.Firebase.bundle)
    implementation(*Dependencies.Data.DataStore.bundle)
    implementation(*Dependencies.Data.Preference.bundle)
}