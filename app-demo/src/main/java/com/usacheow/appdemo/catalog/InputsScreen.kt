package com.usacheow.appdemo.catalog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.usacheow.corecommon.container.IconValue
import com.usacheow.corecommon.container.TextValue
import com.usacheow.corecommon.money.CurrencyType
import com.usacheow.coreuicompose.tools.getTopInset
import com.usacheow.coreuicompose.uikit.barcopy.SimpleTopAppBar
import com.usacheow.coreuicompose.uikit.textfield.AmountFormatter
import com.usacheow.coreuicompose.uikit.textfield.CardNumberFormatter
import com.usacheow.coreuicompose.uikit.textfield.PhoneNumberFormatter
import com.usacheow.coreuicompose.uikit.textfield.SimpleTextField
import com.usacheow.coreuicompose.uikit.textfield.SimpleTextFieldConfig
import com.usacheow.coreuicompose.uikit.textfield.SimpleTextFieldIcon
import com.usacheow.coreuitheme.compose.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputsScreen(navController: NavHostController) {
    val scrollBehavior = remember { TopAppBarDefaults.pinnedScrollBehavior() }

    var cardNumberInputValue by remember { mutableStateOf("") }
    var phoneNumberInputValue by remember { mutableStateOf("") }
    var amountInputValue by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SimpleTopAppBar(
                title = TextValue.Simple("Inputs tiles"),
                navigationIcon = AppTheme.specificIcons.back to navController::popBackStack,
                contentPadding = getTopInset(),
                scrollBehavior = scrollBehavior,
            )
        }
    ) {
        Column {
            SimpleTextField(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                value = cardNumberInputValue,
                onValueChange = CardNumberFormatter.onValueChanged { cardNumberInputValue = it },
                visualTransformation = CardNumberFormatter.visualTransformation(),
                placeholderValue = CardNumberFormatter.placeholder(),
                labelValue = TextValue.Simple("Card number"),
                leadingIconValue = SimpleTextFieldIcon(IconValue.Vector(AppTheme.specificIcons.creditCard)),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                shape = SimpleTextFieldConfig.shape(),
            )
            SimpleTextField(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp, bottom = 8.dp)
                    .fillMaxWidth(),
                value = phoneNumberInputValue,
                onValueChange = PhoneNumberFormatter.onValueChanged { phoneNumberInputValue = it },
                visualTransformation = PhoneNumberFormatter.visualTransformation(),
                placeholderValue = PhoneNumberFormatter.placeholder(),
                labelValue = TextValue.Simple("Phone number"),
                leadingIconValue = SimpleTextFieldIcon(IconValue.Vector(AppTheme.specificIcons.phone)),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                shape = SimpleTextFieldConfig.shape(),
            )
            SimpleTextField(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp, bottom = 8.dp)
                    .fillMaxWidth(),
                value = amountInputValue,
                onValueChange = AmountFormatter.onValueChanged { amountInputValue = it },
                visualTransformation = AmountFormatter.visualTransformation(CurrencyType.RUB),
                placeholderValue = TextValue.Simple("Enter amount"),
                labelValue = TextValue.Simple("Transfer amount"),
                leadingIconValue = SimpleTextFieldIcon(IconValue.Vector(AppTheme.specificIcons.currencyExchange)),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                shape = SimpleTextFieldConfig.shape(),
            )
        }
    }
}