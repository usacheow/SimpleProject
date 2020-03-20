package com.usacheow.coreuikit.utils.textinput

interface IFormatter {

    fun formatText(cleanText: String): String

    fun cleanText(formattedText: String): String
}