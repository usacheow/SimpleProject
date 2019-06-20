package com.kapmayn.core.utils

import android.content.res.Resources
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

inline fun <reified TYPE> Gson.parseJsonFromAsset(resources: Resources, fileName: String): TYPE? {
    var topics: TYPE? = null
    var reader: BufferedReader? = null
    try {
        reader = BufferedReader(InputStreamReader(resources.assets.open(fileName), "UTF-8"))
        topics = fromJson<TYPE>(reader, TYPE::class.java)
    } catch (e: IOException) {

    } finally {
        reader?.close()
    }

    return topics
}