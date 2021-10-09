plugins {
    id("library-config")
    id("navigation-config")
}

dependencies {
    api(project(Modules.CORE.path))
    api(*Dependencies.Tests.Unit.bundle)

    implementation(*Dependencies.Data.Gson.bundle)
}