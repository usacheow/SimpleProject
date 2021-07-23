package plugin

import Dependencies
import com.android.build.gradle.BaseExtension
import implementation
import kapt
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.DependencyHandlerScope

class ComposeConfigPlugin : BaseConfigPlugin() {

    override fun BaseExtension.configAndroid(project: Project) {
        buildFeatures.compose = true

        composeOptions {
            kotlinCompilerVersion = Dependencies.General.Kotlin.version
            kotlinCompilerExtensionVersion = Dependencies.Android.Compose.version
        }
    }

    override fun DependencyHandlerScope.addDependencies(project: Project) {
        implementation(*Dependencies.Android.Compose.impl)
    }
}