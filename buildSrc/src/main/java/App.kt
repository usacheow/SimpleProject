object App {

    const val minSdk = 21
    const val targetSdk = 31
    const val compileSdk = 31
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