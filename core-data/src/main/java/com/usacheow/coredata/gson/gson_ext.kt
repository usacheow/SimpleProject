package com.usacheow.coredata.gson

import android.content.res.Resources
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.Reader

inline fun <reified TYPE> Gson.parseJsonFromAsset(resources: Resources, fileName: String): TYPE? {
    var value: TYPE? = null
    var reader: BufferedReader? = null
    try {
        reader = BufferedReader(InputStreamReader(resources.assets.open(fileName), "UTF-8"))
        value = fromJson(reader)
    } catch (e: IOException) {
    } finally {
        reader?.close()
    }

    return value
}

inline fun <reified TYPE> Gson.fromJson(reader: Reader): TYPE = fromJson(reader, TYPE::class.java)