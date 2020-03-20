package com.usacheow.coreuikit.utils.biometric

sealed class SignInResult

object SignInSuccess : SignInResult()
data class SignInError(val errorText: String? = null) : SignInResult()