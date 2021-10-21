package plugin

import Libs
import implementation
import org.gradle.api.Project
import org.gradle.api.plugins.PluginContainer
import org.gradle.kotlin.dsl.DependencyHandlerScope

class NavigationConfigPlugin : BaseConfigPlugin() {

    override fun PluginContainer.applyPlugins(project: Project) {
        apply(Libs.plugin.navigation)
    }

    override fun DependencyHandlerScope.addDependencies(project: Project) {
        implementation(*Libs.bundle.navigation)
    }
}