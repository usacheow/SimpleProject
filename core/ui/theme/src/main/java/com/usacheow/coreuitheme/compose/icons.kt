package com.usacheow.coreuitheme.compose

import androidx.annotation.DrawableRes
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
import androidx.compose.material.icons.outlined.AutoMode
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.usacheow.corecommon.container.IconValue
import com.usacheow.coreuitheme.R

object SpecificIcons {
    val logo: IconValue = resource(R.drawable.ic_logo)
    val back: IconValue = vector(Icons.Filled.ArrowBack)
    val add: IconValue = vector(Icons.Filled.Add)
    val account: IconValue = vector(Icons.Filled.AccountCircle)
    val navigateNext: IconValue = vector(Icons.Filled.NavigateNext)
    val creditCard: IconValue = vector(Icons.Filled.CreditCard)
    val currencyExchange: IconValue = vector(Icons.Filled.CurrencyExchange)
    val phone: IconValue = vector(Icons.Filled.Phone)
    val error: IconValue = vector(Icons.Filled.Error)
    val fingerprint: IconValue = vector(Icons.Filled.Fingerprint)
    val delete: IconValue = vector(Icons.Filled.Delete)
    val done: IconValue = vector(Icons.Filled.Done)
    val settings: IconValue = vector(Icons.Outlined.Settings)
    val themeSystem: IconValue = vector(Icons.Outlined.AutoMode)
    val themeDark: IconValue = vector(Icons.Outlined.DarkMode)
    val themeLight: IconValue = vector(Icons.Outlined.LightMode)
}

private fun vector(value: ImageVector) = IconValue.Vector(value)
private fun resource(@DrawableRes value: Int) = IconValue.ResVector(value)