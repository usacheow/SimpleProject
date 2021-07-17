package plugin

import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.PluginContainer
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.dependencies

abstract class BaseConfigPlugin : Plugin<Project> {

    private val Project.android: BaseExtension
        get() = extensions.findByName("android") as? BaseExtension
            ?: error("Not an Android module: $name")

    override fun apply(project: Project) = with(project) {
        plugins.applyPlugins(project)
        android.configAndroid(project)
        dependencies { addDependencies(project) }
    }

    protected open fun PluginContainer.applyPlugins(project: Project) {}

    protected open fun BaseExtension.configAndroid(project: Project) {}

    protected open fun DependencyHandlerScope.addDependencies(project: Project) {}
}