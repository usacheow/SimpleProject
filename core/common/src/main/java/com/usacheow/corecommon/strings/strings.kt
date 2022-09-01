package com.usacheow.corecommon.strings

internal val strings = mapOf(
    key(StringKey.ConnectionSuccessMessage) to mapOf(
        SupportedLanguageKey.Ru to "Соединение восстановлено",
        SupportedLanguageKey.En to "Connection restored",
    ),
    key(StringKey.ConnectionErrorMessage) to mapOf(
        SupportedLanguageKey.Ru to "Отсутствует подключение к сети",
        SupportedLanguageKey.En to "No network connection",
    ),
    key(StringKey.UnknownErrorMessage) to mapOf(
        SupportedLanguageKey.Ru to "Произошла ошибка. Попробуйте повторить позднее",
        SupportedLanguageKey.En to "An error has occurred. Try again later",
    ),

    key(StringKey.BbExample) to mapOf(
        SupportedLanguageKey.Ru to "Пример",
        SupportedLanguageKey.En to "Example",
    ),

    key(StringKey.NumPadForget) to mapOf(
        SupportedLanguageKey.Ru to "Забыл код",
        SupportedLanguageKey.En to "Forgot",
    ),
)