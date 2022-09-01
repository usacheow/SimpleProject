package com.usacheow.corecommon.strings

import android.icu.text.PluralRules
import java.util.Locale

class StringHolder(locale: Locale = DEFAULT_LOCALE) {

    private val language = locale.toSupportedLanguageKey()
    private var rule = PluralRules.forLocale(locale)

    operator fun invoke(stringKey: StringKey): String {
        val key = key(stringKey)
        val value = strings[key]!!

        return value[language] ?: value[SupportedLanguageKey.Default]!!
    }

    operator fun invoke(stringKey: StringKey, quantity: Int): String {
        val pluralKey = rule.select(quantity.toDouble()).let(PluralKey::get) ?: PluralKey.Other

        val key = key(stringKey, pluralKey)
        val defaultKey = key(stringKey, PluralKey.Other)
        val value = strings[key] ?: strings[defaultKey]!!

        return value[language] ?: value[SupportedLanguageKey.Default]!!
    }
}