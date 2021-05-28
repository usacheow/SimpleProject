package com.usacheow.coreui.utils.root

import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

interface RootChecker {

    operator fun invoke(onRootFound: () -> Unit)
}

class SimpleRootChecker : RootChecker {

    override operator fun invoke(onRootFound: () -> Unit) {
        if (hasRoot()) {
            onRootFound()
        }
    }

    private fun hasRoot() = checkRootByBuildTags() || checkRootByFiles() || checkRootByCommand()

    private fun checkRootByBuildTags(): Boolean {
        val buildTags = android.os.Build.TAGS
        return buildTags != null && buildTags.contains("test-keys")
    }

    private fun checkRootByFiles(): Boolean {
        val paths = arrayOf(
            "/system/app/Superuser.apk",
            "/sbin/su",
            "/system/bin/su",
            "/system/xbin/su",
            "/data/local/xbin/su",
            "/data/local/bin/su",
            "/system/sd/xbin/su",
            "/system/bin/failsafe/su",
            "/data/local/su",
            "/su/bin/su"
        )

        return paths.firstOrNull {
            File(it).exists()
        } != null
    }

    private fun checkRootByCommand(): Boolean {
        var process: Process? = null

        return try {
            process = Runtime.getRuntime().exec(arrayOf("/system/xbin/which", "su"))

            BufferedReader(InputStreamReader(process.inputStream)).readLine() != null
        } catch (t: Throwable) {
            false
        } finally {
            process?.destroy()
        }
    }
}