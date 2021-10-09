package plugin

import Dependencies
import implementation
import kapt
import org.gradle.api.Project
import org.gradle.api.plugins.PluginContainer
import org.gradle.kotlin.dsl.DependencyHandlerScope

class DaggerConfigPlugin : BaseConfigPlugin() {

    override fun PluginContainer.applyPlugins(project: Project) {
        apply(Dependencies.Dagger.plugin)
    }

    override fun DependencyHandlerScope.addDependencies(project: Project) {
        implementation(*Dependencies.Dagger.bundle)
        kapt(Dependencies.Dagger.kapt)
        kapt(Dependencies.Dagger.kaptViewModel)
    }
}