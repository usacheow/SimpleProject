plugins {
    id("library-config")
    id("navigation-config")
}

dependencies {
    api(*Dependencies.Tests.Unit.impl)

    implementation(*Dependencies.Data.Gson.impl)
}