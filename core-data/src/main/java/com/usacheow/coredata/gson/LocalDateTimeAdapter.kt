package com.usacheow.coredata.gson

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.time.LocalDateTime

class LocalDateTimeAdapter : TypeAdapter<LocalDateTime>() {

    override fun write(jsonWriter: JsonWriter, value: LocalDateTime?) {
        jsonWriter.value(value.toString())
    }

    override fun read(jsonReader: JsonReader): LocalDateTime? {
        return LocalDateTime.parse(jsonReader.nextString())
    }
}