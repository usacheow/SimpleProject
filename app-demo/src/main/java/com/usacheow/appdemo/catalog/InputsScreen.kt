package com.usacheow.appdemo.catalog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.usacheow.corecommon.container.TextValue
import com.usacheow.coreuicompose.tools.getTopInset
import com.usacheow.coreuicompose.uikit.SimpleTopAppBar
import com.usacheow.coreuitheme.R
import com.usacheow.coreuitheme.compose.AppTheme
import java.lang.StringBuilder
import kotlin.math.min

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputsScreen(navController: NavHostController) {
    val scrollBehavior = remember { TopAppBarDefaults.pinnedScrollBehavior() }

    var firstInputValue by remember { mutableStateOf("") }
    var secondInputValue by remember { mutableStateOf("") }

    fun onCardNumberChanged(value: String) {
        secondInputValue = value.take(16)
    }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SimpleTopAppBar(
                title = TextValue.Simple("Inputs tiles"),
                navigationIcon = R.drawable.ic_back to navController::popBackStack,
                contentPadding = getTopInset(),
                scrollBehavior = scrollBehavior,
            )
        }
    ) {
        Column {
            TextField(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                value = firstInputValue,
                onValueChange = { firstInputValue = it },
                enabled = true,
                readOnly = false,
                label = { Text("Label") },
                placeholder = { Text("Placeholder") },
                leadingIcon = { Icon(painter = painterResource(R.drawable.ic_user), contentDescription = null) },
                trailingIcon = { Icon(painter = painterResource(R.drawable.ic_date), contentDescription = null) },
                isError = false,
                visualTransformation = VisualTransformation.None,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = true,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {}
                ),
                shape = AppTheme.shapes.medium,
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                ),
            )
            OutlinedTextField(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp, bottom = 8.dp)
                    .fillMaxWidth(),
                value = secondInputValue,
                onValueChange = CardNumberFormatted.onValueChanged(::onCardNumberChanged),
                visualTransformation = CardNumberFormatted.visualTransformation(),
                enabled = true,
                readOnly = false,
                placeholder = { Text("XXXX-XXXX-XXXX-XXXX") },
                leadingIcon = { Icon(painter = painterResource(R.drawable.ic_user), contentDescription = null) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                shape = AppTheme.shapes.medium,
            )
            Text(
                text = "Inputted card number = $secondInputValue",
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp),
            )
        }
    }
}

private object CardNumberFormatted {

    private const val cardNumberLength = 16
    private const val formattedCardNumberLength = cardNumberLength + 3
    private const val divider = "-"
    private const val dividerLength = divider.length

    private val positions = listOf(4, 8, 12, 16)

    fun onValueChanged(action: (String) -> Unit) = { value: String ->
        action(value.filter { it.isDigit() }.take(cardNumberLength))
    }

    fun visualTransformation() = object : VisualTransformation {

        val offsetMapping = object : OffsetMapping {

            override fun originalToTransformed(offset: Int): Int {
                positions.forEachIndexed { index, position ->
                    if (offset < position) return offset + dividerLength * index
                }
                return formattedCardNumberLength
            }

            override fun transformedToOriginal(offset: Int): Int {
                positions.forEachIndexed { index, position ->
                    if (offset < position * (index + 1) + dividerLength * index) return offset - dividerLength * index
                }
                return cardNumberLength
            }
        }

        override fun filter(text: AnnotatedString): TransformedText {
            return TransformedText(AnnotatedString(format(text.text)), offsetMapping)
        }

        private fun format(text: String): String {
            if (text.isEmpty()) return text

            val formattedTextBuilder = StringBuilder()
            positions.forEachIndexed { index, position ->
                if (position - 4 >= text.length) return@forEachIndexed

                formattedTextBuilder.append(text.substring(position - 4, min(position, text.length)))

                if (formattedTextBuilder.length == position + dividerLength * index) {
                    formattedTextBuilder.append(divider)
                }
            }

            return formattedTextBuilder.toString().take(formattedCardNumberLength)
        }
    }
}