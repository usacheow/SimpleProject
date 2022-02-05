package com.usacheow.core

import android.telephony.PhoneNumberUtils

private const val EXPECTED_PHONE_NUMBER_LENGTH = 11

fun String.normalizedPhoneNumber(): String {
    var phoneNumber = filter { it.isDigit() } /*.substringAfter("7")*/
    phoneNumber = when {
        this.length == EXPECTED_PHONE_NUMBER_LENGTH && this.first() == '8' -> phoneNumber.replaceFirst('8', '7')
        else -> phoneNumber
    }

    return phoneNumber
}

fun String.formatPhoneNumber(): String {
    var phoneNumber = when {
        this.length == EXPECTED_PHONE_NUMBER_LENGTH && this.first() == '8' -> this.replaceFirst('8', '7')
        else -> this
    }
    phoneNumber = when (this.length) {
        EXPECTED_PHONE_NUMBER_LENGTH -> "+$phoneNumber"
        else -> phoneNumber
    }
    return PhoneNumberUtils.formatNumber(phoneNumber, "ru") ?: this
}

fun String.isPhoneNumberValid(): Boolean {
    return normalizedPhoneNumber().length == EXPECTED_PHONE_NUMBER_LENGTH
}