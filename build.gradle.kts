buildscript {

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(Dependencies.General.Gradle.path)
        classpath(Dependencies.General.Kotlin.path)
        classpath(Dependencies.Dagger.path)
        classpath(Dependencies.General.Gms.path)
        classpath(Dependencies.Firebase.path)
        classpath(Dependencies.Android.Navigation.path)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}