package com.usacheow.corecommon.model

import com.usacheow.corecommon.ext.formatToMoney
import java.time.ZonedDateTime
import java.util.Currency
import java.util.UUID
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias DateTime = String
typealias Timestamp = Long
typealias Token = String
typealias PhoneNumber = String
typealias FormattedMoneyWithCurrency = String
typealias FormattedMoney = String
typealias CurrencyIso = Int
typealias FullUrl = String
typealias PartialUrl = String

fun generateCurrentDateTime() = ZonedDateTime.now().toString()
fun generateRequestId() = UUID.randomUUID().toString()

@Serializable
data class PhoneNumberData(
    val code: Int, // eg 7
    val number: Long, // eg 1234567890
) {

    fun getPhoneNumber(): PhoneNumber = "$code$number"
    fun getPhoneNumberWithPlus(): PhoneNumber = "+${getPhoneNumber()}"
}

@Serializable
data class MoneyDto(
    @SerialName("currency") val currency: CurrencyType,
    @SerialName("value") val value: Double,
) {

    fun getFormattedMoney() = value.formatToMoney()

    fun getFormattedMoneyWithCurrency() = "%s %s".format(getFormattedMoney(), currency.symbol)
}

@Serializable
enum class CurrencyType {
    @SerialName("643") RUB,
    @SerialName("840") USD,
    @SerialName("978") EUR;

    val symbol
        get() = runCatching {
            Currency.getInstance(name).symbol
        }.getOrNull().orEmpty()

    val displayName
        get() = runCatching {
            Currency.getInstance(name).displayName
        }.getOrNull().orEmpty()
}