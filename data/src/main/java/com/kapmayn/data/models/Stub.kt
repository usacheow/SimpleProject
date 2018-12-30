package com.kapmayn.data.models

import com.google.gson.annotations.SerializedName

data class Stub(
    @SerializedName("stubId")
    val id: Long,
    @SerializedName("stub_field_first")
    val stubFieldFirst: String,
    @SerializedName("stub_field_second")
    val stubFieldSecond: String
)