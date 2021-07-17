package plugin

import Dependencies
import implementation
import kapt
import org.gradle.api.Project
import org.gradle.kotlin.dsl.DependencyHandlerScope

class GlideConfigPlugin : BaseConfigPlugin() {

    override fun DependencyHandlerScope.addDependencies(project: Project) {
        implementation(*Dependencies.Data.Glide.impl)
        kapt(Dependencies.Data.Glide.kapt)
    }
}