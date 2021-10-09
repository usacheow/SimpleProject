package plugin

import Dependencies
import implementation
import kapt
import org.gradle.api.Project
import org.gradle.kotlin.dsl.DependencyHandlerScope

class RoomConfigPlugin : BaseConfigPlugin() {

    override fun DependencyHandlerScope.addDependencies(project: Project) {
        implementation(*Dependencies.Data.Room.bundle)
        kapt(Dependencies.Data.Room.kapt)
    }
}