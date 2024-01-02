import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.fileTree

private val Project.android: BaseExtension
    get() = extensions.findByName("android") as? BaseExtension ?: error("Not an Android module: $name")

fun Project.common() {
    plugins.apply {
        apply("kotlin-android")
        apply("kotlin-parcelize")
        apply("kotlinx-serialization")
        apply("kotlin-kapt")
        apply("org.jmailen.kotlinter")
    }

    android.apply {
        compileSdkVersion(App.compileSdk)

        defaultConfig {
            minSdk = App.minSdk
            targetSdk = App.targetSdk

            vectorDrawables.useSupportLibrary = true
            testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
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
            sourceCompatibility = JavaVersion.VERSION_19
            targetCompatibility = JavaVersion.VERSION_19
        }
    }

    dependencies {
        coreLibraryDesugaring(libs("desugar"))
        implementation(project.fileTree("include" to "*.jar", "dir" to "libs"))

        implementation(bundle("kotlin"))
        implementation(bundle("coroutines"))
    }
}

fun Project.compose() {
    android.apply {
        buildFeatures.compose = true

        composeOptions {
            kotlinCompilerExtensionVersion = version("composeCompilerVersion")
        }
    }

    dependencies {
        implementation(libs("composeCompiler"))
        implementation(libs("composeRuntime"))
    }
}

fun Project.navigation() {
    dependencies {
        implementation(bundle("navigation"))
    }
}

fun Project.dagger() {
    dependencies {
        implementation(bundle("di"))
    }
}

fun Project.room() {
    dependencies {
        implementation(bundle("room"))
        kapt(libs("roomCompiler"))
    }
}

fun Project.lifecycle() {
    dependencies {
        implementation(bundle("lifecycle"))
        kapt(libs("lifecycleCompiler"))
    }
}