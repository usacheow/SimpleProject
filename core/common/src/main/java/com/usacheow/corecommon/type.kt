package com.usacheow.corecommon

import java.time.ZonedDateTime
import java.util.Currency
import java.util.UUID
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias DateTime = String
typealias Timestamp = Long
typealias Token = String
typealias PhoneNumber = String
typealias MaskedPhoneNumber = String

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
    fun getFormattedMoney() = "%s %s".format(value.formatDigits(), currency.symbol)
}

@Serializable
enum class CurrencyType {
    @SerialName("RUB")
    RUB,
    @SerialName("USD")
    USD,
    @SerialName("EUR")
    EUR,
    @SerialName("GBP")
    GBP,
    @SerialName("HKD")
    HKD,
    @SerialName("CHF")
    CHF,
    @SerialName("JPY")
    JPY,
    @SerialName("CNY")
    CNY,
    @SerialName("TRY")
    TRY;

    val symbol
        get() = runCatching {
            Currency.getInstance(name).symbol
        }.getOrNull().orEmpty()

    val displayName
        get() = runCatching {
            Currency.getInstance(name).displayName
        }.getOrNull().orEmpty()
}