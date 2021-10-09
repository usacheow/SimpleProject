package plugin

import Dependencies
import implementation
import kapt
import org.gradle.api.Project
import org.gradle.kotlin.dsl.DependencyHandlerScope

class LifecycleConfigPlugin : BaseConfigPlugin() {

    override fun DependencyHandlerScope.addDependencies(project: Project) {
        implementation(*Dependencies.Android.Lifecycle.bundle)
        kapt(Dependencies.Android.Lifecycle.kapt)
    }
}