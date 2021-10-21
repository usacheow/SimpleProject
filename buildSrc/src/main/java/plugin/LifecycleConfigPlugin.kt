package plugin

import Libs
import implementation
import kapt
import org.gradle.api.Project
import org.gradle.kotlin.dsl.DependencyHandlerScope

class LifecycleConfigPlugin : BaseConfigPlugin() {

    override fun DependencyHandlerScope.addDependencies(project: Project) {
        implementation(*Libs.bundle.lifecycle)
        kapt(Libs.bundle.lifecycleKapt)
    }
}