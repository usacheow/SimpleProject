package com.usacheow.coreui.utils.textinput

import com.google.android.material.textfield.TextInputEditText
import ru.tinkoff.decoro.FormattedTextChangeListener
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.slots.PredefinedSlots
import ru.tinkoff.decoro.slots.Slot
import ru.tinkoff.decoro.slots.ValueInterpreter
import ru.tinkoff.decoro.watchers.FormatWatcher
import ru.tinkoff.decoro.watchers.MaskFormatWatcher

private const val PHONE_NUMBER_CODE_POSITION = 1

class PhoneNumberFormatter(
    private val inputEditText: TextInputEditText,
    private val onPhoneNumberCompleted: (String) -> Unit,
    private val onPhoneNumberInputChanged: (String) -> Unit
) {

    private val valueInterpreter = ValueInterpreter { character ->
        return@ValueInterpreter when (character) {
            '8' -> '7'
            else -> character
        }
    }
    private val formattedTextChangeListener = object : FormattedTextChangeListener {
        override fun onTextFormatted(formatter: FormatWatcher, newFormattedText: String) {
            val unformattedPhoneNumber = formatter.mask.toUnformattedString().removePrefix("+")
            if (newFormattedText.length == formatter.mask.size) {
                onPhoneNumberCompleted(unformattedPhoneNumber)
            }
            onPhoneNumberInputChanged(unformattedPhoneNumber)
        }

        override fun beforeFormatting(oldValue: String, newValue: String) = false
    }

    init {
        initMaskFormatWatcher()
    }

    private fun initMaskFormatWatcher() {
        val slots = Slot.copySlotArray(PredefinedSlots.RUS_PHONE_NUMBER)
        slots[PHONE_NUMBER_CODE_POSITION].setValueInterpreter(valueInterpreter)

        val mask = MaskImpl(slots, true).apply { isHideHardcodedHead = true }
        with(MaskFormatWatcher(mask)) {
            installOn(inputEditText)
            setCallback(formattedTextChangeListener)
        }
    }

}
