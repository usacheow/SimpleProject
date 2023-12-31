import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.getByType

fun DependencyHandler.kapt(vararg list: Any) {
    list.forEach { dependency ->
        add("kapt", dependency)
    }
}

fun DependencyHandler.implementation(vararg list: Any) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

fun DependencyHandler.api(vararg list: Any) {
    list.forEach { dependency ->
        add("api", dependency)
    }
}

fun DependencyHandler.androidTestImplementation(vararg list: Any) {
    list.forEach { dependency ->
        add("androidTestImplementation", dependency)
    }
}

fun DependencyHandler.testImplementation(vararg list: Any) {
    list.forEach { dependency ->
        add("testImplementation", dependency)
    }
}

fun DependencyHandler.coreLibraryDesugaring(vararg list: Any) {
    list.forEach { dependency ->
        add("coreLibraryDesugaring", dependency)
    }
}

fun Project.libs(name: String) = rootProject.extensions
    .getByType(VersionCatalogsExtension::class)
    .named("libs")
    .findLibrary(name)
    .get()

fun Project.bundle(name: String) = rootProject.extensions
    .getByType(VersionCatalogsExtension::class)
    .named("libs")
    .findBundle(name)
    .get()

fun Project.plugins(name: String): String = rootProject.extensions
    .getByType(VersionCatalogsExtension::class)
    .named("libs")
    .findVersion(name)
    .get()
    .displayName

fun Project.version(name: String): String = rootProject.extensions
    .getByType(VersionCatalogsExtension::class)
    .named("libs")
    .findVersion(name)
    .get()
    .displayName