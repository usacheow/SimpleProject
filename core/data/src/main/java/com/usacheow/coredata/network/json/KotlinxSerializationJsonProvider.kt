package com.usacheow.coredata.network.json

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.contextual
import javax.inject.Provider

class KotlinxSerializationJsonProvider() : Provider<Json> {

    @OptIn(ExperimentalSerializationApi::class)
    override fun get(): Json {
        return Json {
            ignoreUnknownKeys = true
            explicitNulls = false
            isLenient = true
            serializersModule = SerializersModule {
                contextual(LocalDateTimeSerializer)
            }
        }
    }
}