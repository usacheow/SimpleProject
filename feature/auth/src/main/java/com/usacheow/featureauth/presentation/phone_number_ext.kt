package com.usacheow.featureauth.presentation

import android.telephony.PhoneNumberUtils
import com.usacheow.corecommon.RU_LOCALE

private const val EXPECTED_PHONE_NUMBER_LENGTH = 11

fun String.normalizedPhoneNumber(): String {
    val phoneNumber = filter { it.isDigit() }
    return when {
        this.length == EXPECTED_PHONE_NUMBER_LENGTH && this.first() == '8' -> phoneNumber.replaceFirst('8', '7')
        else -> phoneNumber
    }
}

fun String.formatPhoneNumber(): String {
    var phoneNumber = this.normalizedPhoneNumber()
    phoneNumber = when (this.length) {
        EXPECTED_PHONE_NUMBER_LENGTH -> "+$phoneNumber"
        else -> phoneNumber
    }
    return PhoneNumberUtils.formatNumber(phoneNumber, RU_LOCALE.language) ?: this
}

fun String.isPhoneNumberValid(): Boolean {
    return normalizedPhoneNumber().length == EXPECTED_PHONE_NUMBER_LENGTH
}