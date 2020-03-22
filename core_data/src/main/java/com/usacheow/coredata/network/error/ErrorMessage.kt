package com.usacheow.coredata.network.error

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ErrorMessage(
    @SerializedName("code")
    @Expose
    val code: String?,
    @SerializedName("detail")
    @Expose
    val detail: String?
)