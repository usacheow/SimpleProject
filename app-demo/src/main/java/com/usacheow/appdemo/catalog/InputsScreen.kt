package com.usacheow.appdemo.catalog

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import com.usacheow.coreuicompose.uikit.duplicate.SimpleTopAppBar
import com.usacheow.coreuicompose.uikit.input.SimpleSelector
import com.usacheow.coreuicompose.uikit.input.SimpleSelectorDropDown
import com.usacheow.coreuicompose.uikit.input.SimpleSelectorDropDownHeader
import com.usacheow.coreuicompose.uikit.input.SimpleSelectorDropDownItem
import com.usacheow.coreuicompose.uikit.input.SimpleSelectorPlaceholder
import com.usacheow.coreuicompose.uikit.input.SimpleTextField
import com.usacheow.coreuicompose.uikit.input.SimpleTextFieldConfig
import com.usacheow.coreuicompose.uikit.input.SimpleTextFieldIcon
import com.usacheow.coreuicompose.uikit.input.formatter.AmountFormatter
import com.usacheow.coreuicompose.uikit.input.formatter.CardNumberFormatter
import com.usacheow.coreuicompose.uikit.input.formatter.PhoneNumberFormatter
import com.usacheow.coreuitheme.compose.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputsScreen(navController: NavHostController) {
    val scrollBehavior = remember { TopAppBarDefaults.pinnedScrollBehavior() }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SimpleTopAppBar(
                title = TextValue.Simple("Inputs"),
                navigationIcon = AppTheme.specificIcons.back to navController::popBackStack,
                contentPadding = getTopInset(),
                scrollBehavior = scrollBehavior,
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
        ) {
            TextFields()
            Selectors()
        }
    }
}

@Composable
private fun TextFields() {
    var cardNumberInputValue by remember { mutableStateOf("") }
    var phoneNumberInputValue by remember { mutableStateOf("") }
    var amountInputValue by remember { mutableStateOf("") }

    SimpleTextField(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
            .fillMaxWidth(),
        value = cardNumberInputValue,
        onValueChange = CardNumberFormatter.onValueChanged { cardNumberInputValue = it },
        visualTransformation = CardNumberFormatter.visualTransformation(),
        placeholderValue = CardNumberFormatter.placeholder(),
        labelValue = TextValue.Simple("Card number"),
        leadingIconValue = SimpleTextFieldIcon(AppTheme.specificIcons.creditCard),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        shape = SimpleTextFieldConfig.shape(),
    )
    SimpleTextField(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
            .fillMaxWidth(),
        value = phoneNumberInputValue,
        onValueChange = PhoneNumberFormatter.onValueChanged { phoneNumberInputValue = it },
        visualTransformation = PhoneNumberFormatter.visualTransformation(),
        placeholderValue = PhoneNumberFormatter.placeholder(),
        labelValue = TextValue.Simple("Phone number"),
        leadingIconValue = SimpleTextFieldIcon(AppTheme.specificIcons.phone),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        shape = SimpleTextFieldConfig.shape(),
    )
    SimpleTextField(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
            .fillMaxWidth(),
        value = amountInputValue,
        onValueChange = AmountFormatter.onValueChanged { amountInputValue = it },
        visualTransformation = AmountFormatter.visualTransformation(CurrencyType.RUB),
        placeholderValue = TextValue.Simple("Enter amount"),
        labelValue = TextValue.Simple("Transfer amount"),
        leadingIconValue = SimpleTextFieldIcon(AppTheme.specificIcons.currencyExchange),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
        shape = SimpleTextFieldConfig.shape(),
    )
}

@Composable
private fun Selectors() {
    val items = listOf("First", "Second", "Third")
    var isExpanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf<String?>(null) }
    val defaultPlaceholder = TextValue.Simple("Selector placeholder")
    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp),
    ) {
        SimpleSelector(
            onClick = { isExpanded = !isExpanded },
            modifier = Modifier.fillMaxWidth(),
        ) {
            val value = selectedItem
                ?.let { TextValue.Simple(it) }
                ?: defaultPlaceholder
            SimpleSelectorPlaceholder(value)
        }

        if (isExpanded) {
            SimpleSelectorDropDown(onDismissRequest = { isExpanded = false }) {
                SimpleSelectorDropDownHeader(defaultPlaceholder)
                items.forEach {
                    Item(it) {
                        isExpanded = false
                        selectedItem = it
                    }
                }
            }
        }
    }
}

@Composable
private fun Item(
    value: String,
    onClick: () -> Unit,
) {
    SimpleSelectorDropDownItem(
        value = TextValue.Simple(value),
        onClick = onClick,
    )
}