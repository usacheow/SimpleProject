package com.usacheow.appdemo.catalog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.usacheow.corecommon.container.textValue
import com.usacheow.corecommon.model.CurrencyType
import com.usacheow.coreuicompose.tools.add
import com.usacheow.coreuicompose.tools.dpSize
import com.usacheow.coreuicompose.tools.get
import com.usacheow.coreuicompose.tools.intOffsetInParent
import com.usacheow.coreuicompose.uikit.status.SimplePopup
import com.usacheow.coreuicompose.uikit.status.SimplePopupItem
import com.usacheow.coreuicompose.uikit.duplicate.SimpleTopAppBar
import com.usacheow.coreuicompose.uikit.input.SimpleSelector
import com.usacheow.coreuicompose.uikit.input.SimpleSelectorPlaceholder
import com.usacheow.coreuicompose.uikit.input.SimpleTextField
import com.usacheow.coreuicompose.uikit.input.SimpleTextFieldConfig
import com.usacheow.coreuicompose.uikit.input.formatter.AmountFormatter
import com.usacheow.coreuicompose.uikit.input.formatter.CardNumberFormatter
import com.usacheow.coreuicompose.uikit.input.formatter.PhoneNumberFormatter
import com.usacheow.coreuitheme.compose.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputsScreen(navController: NavHostController) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SimpleTopAppBar(
                title = "Inputs".textValue(),
                navigationIcon = AppTheme.specificIcons.back to navController::popBackStack,
                scrollBehavior = scrollBehavior,
            )
        },
    ) {
        Column(
            modifier = Modifier
                .padding(it)
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
    val phoneNumberFormatter = PhoneNumberFormatter()
    val cardNumberFormatter = CardNumberFormatter()
    val amountFormatter = AmountFormatter(CurrencyType.RUB)

    SimpleTextField(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
            .fillMaxWidth(),
        value = cardNumberInputValue,
        onValueChange = cardNumberFormatter.onValueChanged { cardNumberInputValue = it },
        visualTransformation = cardNumberFormatter.visualTransformation(
            inputtedPartColor = AppTheme.specificColorScheme.symbolPrimary,
            otherPartColor = AppTheme.specificColorScheme.symbolSecondary,
        ),
        placeholder = { Text(cardNumberFormatter.placeholder().get()) },
        label = { Text("Card number") },
        leadingIcon = { Icon(AppTheme.specificIcons.creditCard.get(), contentDescription = null) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        shape = SimpleTextFieldConfig.shape(),
    )
    SimpleTextField(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
            .fillMaxWidth(),
        value = phoneNumberInputValue,
        onValueChange = phoneNumberFormatter.onValueChanged { phoneNumberInputValue = it },
        visualTransformation = phoneNumberFormatter.visualTransformation(
            inputtedPartColor = AppTheme.specificColorScheme.symbolPrimary,
            otherPartColor = AppTheme.specificColorScheme.symbolSecondary,
        ),
        placeholder = { Text(phoneNumberFormatter.placeholder().get()) },
        label = { Text("Phone number") },
        leadingIcon = { Icon(AppTheme.specificIcons.phone.get(), contentDescription = null) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        shape = SimpleTextFieldConfig.shape(),
    )
    SimpleTextField(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
            .fillMaxWidth(),
        value = amountInputValue,
        onValueChange = amountFormatter.onValueChanged { amountInputValue = it },
        visualTransformation = amountFormatter.visualTransformation(
            inputtedPartColor = AppTheme.specificColorScheme.symbolPrimary,
            otherPartColor = AppTheme.specificColorScheme.symbolSecondary,
        ),
        placeholder = { Text("Enter amount") },
        label = { Text("Transfer amount") },
        leadingIcon = {
            Icon(AppTheme.specificIcons.currencyExchange.get(), contentDescription = null)
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
        shape = SimpleTextFieldConfig.shape(),
    )
}

@Composable
private fun Selectors() {
    val items = listOf("First", "Second", "Third")
    var isExpanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf<String?>(null) }
    val density = LocalDensity.current
    val layoutCoordinates = remember { mutableStateOf<LayoutCoordinates?>(null) }

    SimpleSelector(
        onClick = { isExpanded = !isExpanded },
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 24.dp)
            .fillMaxWidth()
            .onGloballyPositioned { layoutCoordinates.value = it },
    ) {
        val value = selectedItem?.textValue() ?: "Selector placeholder".textValue()
        SimpleSelectorPlaceholder(value)
    }

    if (isExpanded) {
        SimplePopup(
            offset = layoutCoordinates.value
                .intOffsetInParent()
                .add(density, y = layoutCoordinates.value.dpSize(density).height + 8.dp),
            modifier = Modifier.width(layoutCoordinates.value.dpSize(density).width),
            onDismissRequest = { isExpanded = false },
        ) {
            items.forEach {
                SimplePopupItem(
                    value = it.textValue(),
                    onClick = {
                        isExpanded = false
                        selectedItem = it
                    },
                )
            }
        }
    }
}