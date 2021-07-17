package plugin

import Dependencies
import implementation
import org.gradle.api.Project
import org.gradle.api.plugins.PluginContainer
import org.gradle.kotlin.dsl.DependencyHandlerScope

class NavigationConfigPlugin : BaseConfigPlugin() {

    override fun PluginContainer.applyPlugins(project: Project) {
        apply(Dependencies.Android.Navigation.plugin)
    }

    override fun DependencyHandlerScope.addDependencies(project: Project) {
        implementation(*Dependencies.Android.Navigation.impl)
    }
}