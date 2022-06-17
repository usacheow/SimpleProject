package com.usacheow.corecommon.model

data class BuildInfo(
    val applicationId: String,
    val buildType: String,
    val versionCode: Int,
    val versionName: String,
    val platform: String = "Android",
) {

    val isDebug: Boolean get() = buildType == "debug"

    val isAlpha: Boolean get() = buildType == "alpha"

    val isRelease: Boolean get() = buildType == "release"
}