package com.usacheow.coredata.json

import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.contextual
import javax.inject.Inject
import javax.inject.Provider

class KotlinxSerializationJsonProvider @Inject constructor() : Provider<Json> {

    override fun get(): Json {
        return Json {
            ignoreUnknownKeys = true
            isLenient = true
            serializersModule = SerializersModule {
                contextual(LocalDateTimeSerializer)
            }
        }
    }
}