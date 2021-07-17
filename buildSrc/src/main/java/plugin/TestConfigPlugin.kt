package plugin

import Dependencies
import com.android.build.gradle.BaseExtension
import testImplementation
import androidTestImplementation
import org.gradle.api.Project
import org.gradle.api.plugins.PluginContainer
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.project

class TestConfigPlugin : BaseConfigPlugin() {

    override fun BaseExtension.configAndroid(project: Project) {
        defaultConfig {
            testInstrumentationRunner = Dependencies.Tests.Unit.runner
        }
    }

    override fun DependencyHandlerScope.addDependencies(project: Project) {
        testImplementation(project(Modules.CORE_UNIT_TEST.path))
        androidTestImplementation(*Dependencies.Tests.Ui.impl)
    }
}