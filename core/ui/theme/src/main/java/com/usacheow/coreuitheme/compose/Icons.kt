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
import androidx.compose.ui.graphics.vector.ImageVector
import com.usacheow.corecommon.container.IconValue

data class SpecificIcons(
    val back: IconValue = vector(Icons.Filled.ArrowBack),
    val add: IconValue = vector(Icons.Filled.Add),
    val account: IconValue = vector(Icons.Filled.AccountCircle),
    val navigateNext: IconValue = vector(Icons.Filled.NavigateNext),
    val creditCard: IconValue = vector(Icons.Filled.CreditCard),
    val currencyExchange: IconValue = vector(Icons.Filled.CurrencyExchange),
    val phone: IconValue = vector(Icons.Filled.Phone),
    val error: IconValue = vector(Icons.Filled.Error),
    val fingerprint: IconValue = vector(Icons.Filled.Fingerprint),
    val delete: IconValue = vector(Icons.Filled.Delete),
    val done: IconValue = vector(Icons.Filled.Done),
)

private fun vector(value: ImageVector) = IconValue.Vector(value)