package com.usacheow.coreuitheme.compose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.CurrencyExchange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.vector.ImageVector

internal val LocalSpecificIcons = staticCompositionLocalOf { SpecificIcons() }

data class SpecificIcons(
    val back: ImageVector = Icons.Default.ArrowBack,
    val add: ImageVector = Icons.Default.Add,
    val account: ImageVector = Icons.Default.AccountCircle,
    val navigateNext: ImageVector = Icons.Default.NavigateNext,
    val creditCard: ImageVector = Icons.Default.CreditCard,
    val currencyExchange: ImageVector = Icons.Default.CurrencyExchange,
    val phone: ImageVector = Icons.Default.Phone,
    val error: ImageVector = Icons.Default.Error,
    val fingerprint: ImageVector = Icons.Default.Fingerprint,
    val delete: ImageVector = Icons.Default.Delete,
    val done: ImageVector = Icons.Default.Done,
)