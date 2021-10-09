plugins {
    id("library-config")
    id("navigation-config")
}

dependencies {
    api(project(Modules.CORE.path))
}