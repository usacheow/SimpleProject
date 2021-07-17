object App {

    const val minSdk = 21
    const val targetSdk = 30
    const val compileSdk = 30
    const val packageName = "com.usacheow.simpleapp"

    val code
        get() = version

    val name
        get() = "$major.$minor.$patch"

    private var version = 1

    private var major = 0
    private var minor = 0
    private var patch = 1
}

object BuildTypes {
    const val releaseSuffix = "-release"
    const val debugSuffix = "-debug"

    const val release = "release"
    const val debug = "debug"
}

object KeystoreParams {
    const val path = "release"
    const val storePassword = ""
    const val keyAlias = ""
    const val keyPassword = ""
}