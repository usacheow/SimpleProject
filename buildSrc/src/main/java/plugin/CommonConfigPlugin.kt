package plugin

import App
import Dependencies
import com.android.build.gradle.BaseExtension
import coreLibraryDesugaring
import implementation
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.PluginContainer
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.fileTree

class LibraryConfigPlugin : CommonConfigPlugin() {

    override val mainPlugin = Dependencies.Android.Library.plugin
}

class AppConfigPlugin : CommonConfigPlugin() {

    override val mainPlugin = Dependencies.Android.App.plugin
}

abstract class CommonConfigPlugin : BaseConfigPlugin() {

    protected abstract val mainPlugin: String

    override fun PluginContainer.applyPlugins(project: Project) {
        apply(mainPlugin)
        apply(Dependencies.General.Kotlin.Android.plugin)
        apply(Dependencies.General.Kotlin.Kapt.plugin)
    }

    override fun BaseExtension.configAndroid(project: Project) {
        compileSdkVersion(App.compileSdk)

        defaultConfig {
            minSdk = App.minSdk
            targetSdk = App.targetSdk

            vectorDrawables.useSupportLibrary = true
        }

        buildFeatures.viewBinding = true

        compileOptions {
            isCoreLibraryDesugaringEnabled = true

            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
    }

    override fun DependencyHandlerScope.addDependencies(project: Project) {
        coreLibraryDesugaring(*Dependencies.General.Desugar.impl)
        implementation(project.fileTree("include" to "*.jar", "dir" to "libs"))

        implementation(*Dependencies.General.Kotlin.impl)
        implementation(*Dependencies.General.Kotlin.Coroutines.impl)
    }
}