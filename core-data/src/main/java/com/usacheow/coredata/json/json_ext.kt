package com.usacheow.coredata.json

import com.usacheow.core.resource.ResourcesWrapper
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.*

inline fun <reified TYPE> Json.parseJsonFromAsset(resources: ResourcesWrapper, fileName: String): TYPE? {
    var value: TYPE? = null
    var reader: BufferedReader? = null
    try {
        value = fromJson(resources.getAssets().open(fileName))
    } catch (e: IOException) {
    } finally {
        reader?.close()
    }

    return value
}

@OptIn(ExperimentalSerializationApi::class)
inline fun <reified TYPE> Json.fromJson(stream: InputStream): TYPE = decodeFromStream(stream)