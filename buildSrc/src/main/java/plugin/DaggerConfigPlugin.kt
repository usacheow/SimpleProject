package plugin

import Libs
import implementation
import kapt
import org.gradle.api.Project
import org.gradle.api.plugins.PluginContainer
import org.gradle.kotlin.dsl.DependencyHandlerScope

class DaggerConfigPlugin : BaseConfigPlugin() {

    override fun PluginContainer.applyPlugins(project: Project) {
        apply(Libs.plugin.hilt)
    }

    override fun DependencyHandlerScope.addDependencies(project: Project) {
        implementation(*Libs.bundle.hilt)
        kapt(Libs.bundle.hiltKapt)
        kapt(Libs.bundle.hiltKaptViewModel)
    }
}