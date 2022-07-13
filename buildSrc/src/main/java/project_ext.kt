import com.android.build.gradle.BaseExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.fileTree

private val Project.android: BaseExtension
    get() = extensions.findByName("android") as? BaseExtension ?: error("Not an Android module: $name")

fun Project.common() {
    plugins.apply {
        apply(Libs.plugin.kotlin_android)
        apply(Libs.plugin.kotlin_parcelize)
        apply(Libs.plugin.kotlin_serialization)
        apply(Libs.plugin.kotlin_kapt)
        apply(Libs.plugin.lint)
    }

    android.apply {
        compileSdkVersion(App.compileSdk)

        defaultConfig {
            minSdk = App.minSdk
            targetSdk = App.targetSdk

            vectorDrawables.useSupportLibrary = true
            testInstrumentationRunner = Libs.bundle.unitTestsRunner
        }

        buildTypes {
            getByName(BuildTypes.debug) {
                isMinifyEnabled = false
            }
            create(BuildTypes.alpha) {
                initWith(getByName(BuildTypes.debug))
                isMinifyEnabled = true
                proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            }
            getByName(BuildTypes.release) {
                isMinifyEnabled = true
                proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            }
        }

        compileOptions {
            isCoreLibraryDesugaringEnabled = true
        }
    }

    dependencies {
        coreLibraryDesugaring(*Libs.bundle.desugar)
        implementation(project.fileTree("include" to "*.jar", "dir" to "libs"))

        implementation(*Libs.bundle.kotlin)
        implementation(*Libs.bundle.coroutines)
    }
}

fun Project.compose() {
    android.apply {
        buildFeatures.compose = true

        composeOptions {
            kotlinCompilerExtensionVersion = Libs.composeCompilerVersion
        }
    }

    dependencies {
        implementation(*Libs.bundle.composeCompiler)
        implementation(*Libs.bundle.composeRuntime)
    }
}

fun Project.navigation() {
    dependencies {
        implementation(*Libs.bundle.navigation)
    }
}

fun Project.dagger() {
    plugins.apply {
        apply(Libs.plugin.hilt)
    }

    dependencies {
        implementation(*Libs.bundle.hilt)
        kapt(Libs.bundle.hiltKapt)
        kapt(Libs.bundle.hiltKaptViewModel)
    }
}

fun Project.room() {
    dependencies {
        implementation(*Libs.bundle.room)
        kapt(Libs.bundle.roomKapt)
    }
}

fun Project.lifecycle() {
    dependencies {
        implementation(*Libs.bundle.lifecycle)
        kapt(Libs.bundle.lifecycleKapt)
    }
}