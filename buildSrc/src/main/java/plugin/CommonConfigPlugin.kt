package plugin

import App
import Libs
import com.android.build.gradle.BaseExtension
import coreLibraryDesugaring
import implementation
import org.gradle.api.Project
import org.gradle.api.plugins.PluginContainer
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.fileTree

class LibraryConfigPlugin : CommonConfigPlugin() {

    override val mainPlugin = Libs.plugin.library
}

class AppConfigPlugin : CommonConfigPlugin() {

    override val mainPlugin = Libs.plugin.application
}

abstract class CommonConfigPlugin : BaseConfigPlugin() {

    protected abstract val mainPlugin: String

    override fun PluginContainer.applyPlugins(project: Project) {
        apply(mainPlugin)
        apply(Libs.plugin.kotlin_android)
        apply(Libs.plugin.kotlin_parcelize)
        apply(Libs.plugin.kotlin_serialization)
        apply(Libs.plugin.kotlin_kapt)
    }

    override fun BaseExtension.configAndroid(project: Project) {
        compileSdkVersion(App.compileSdk)

        defaultConfig {
            minSdk = App.minSdk
            targetSdk = App.targetSdk

            vectorDrawables.useSupportLibrary = true
            testInstrumentationRunner = Libs.bundle.unitTestsRunner
        }

        buildFeatures.viewBinding = true
        buildFeatures.compose = true

        composeOptions {
            kotlinCompilerExtensionVersion = Libs.composeVersion
        }

        compileOptions {
            isCoreLibraryDesugaringEnabled = true
        }
    }

    override fun DependencyHandlerScope.addDependencies(project: Project) {
        coreLibraryDesugaring(*Libs.bundle.desugar)
        implementation(project.fileTree("include" to "*.jar", "dir" to "libs"))

        implementation(*Libs.bundle.kotlin)
        implementation(*Libs.bundle.coroutines)
        implementation(*Libs.bundle.uiComposeCompiler)
        implementation(*Libs.bundle.uiComposeRuntime)
    }
}