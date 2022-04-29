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
import com.usacheow.corecommon.container.IconValue

internal val LocalSpecificIcons = staticCompositionLocalOf { SpecificIcons() }

data class SpecificIcons(
    val back: IconValue = IconValue.Vector(Icons.Default.ArrowBack),
    val add: IconValue = IconValue.Vector(Icons.Default.Add),
    val account: IconValue = IconValue.Vector(Icons.Default.AccountCircle),
    val navigateNext: IconValue = IconValue.Vector(Icons.Default.NavigateNext),
    val creditCard: IconValue = IconValue.Vector(Icons.Default.CreditCard),
    val currencyExchange: IconValue = IconValue.Vector(Icons.Default.CurrencyExchange),
    val phone: IconValue = IconValue.Vector(Icons.Default.Phone),
    val error: IconValue = IconValue.Vector(Icons.Default.Error),
    val fingerprint: IconValue = IconValue.Vector(Icons.Default.Fingerprint),
    val delete: IconValue = IconValue.Vector(Icons.Default.Delete),
    val done: IconValue = IconValue.Vector(Icons.Default.Done),
)