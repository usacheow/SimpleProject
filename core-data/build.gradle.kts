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
    api(*Dependencies.Data.Requests.impl)
    api(*Dependencies.Data.Gson.impl)

    implementation(*Dependencies.Firebase.impl)
    implementation(*Dependencies.Data.DataStore.impl)
    implementation(*Dependencies.Data.Preference.impl)
}