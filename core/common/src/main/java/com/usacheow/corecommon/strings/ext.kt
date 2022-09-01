package com.usacheow.corecommon.strings

import java.util.Locale

val DEFAULT_LOCALE: Locale get() = Locale.getDefault()
val RU_LOCALE: Locale get() = Locale("ru")

enum class PluralKey {
    Zero, One, Two, Few, Many, Other;
    companion object {
        fun get(name: String) = values().firstOrNull { it.name.equals(name, ignoreCase = true) }
    }
}

enum class SupportedLanguageKey {
    Ru, En;
    companion object {
        val Default = En
        fun get(name: String) = values().firstOrNull { it.name.equals(name, ignoreCase = true) }
    }
}

fun Locale.toSupportedLanguageKey() = SupportedLanguageKey.get(country) ?: SupportedLanguageKey.Default

internal fun key(stringKey: StringKey) = stringKey.name
internal fun key(stringKey: StringKey, pluralKey: PluralKey) = "${stringKey.name}_${pluralKey.name}"