package com.usacheow.coreuikit.views

import android.content.Context
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.AttributeSet
import androidx.annotation.ColorRes
import androidx.appcompat.widget.AppCompatTextView
import com.usacheow.coreuikit.R
import com.usacheow.coreuikit.utils.ext.color
import com.usacheow.coreuikit.utils.ext.toRubValue
import java.util.Locale

class BalanceTextView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : AppCompatTextView(context, attrs, defStyle) {

    var primaryColor = R.color.colorText
    var secondaryColor = R.color.colorTextSecondary
    var separator = Separator.UNDEFINED

    private val locale = Locale("ru")

    init {
        if (attrs != null) {
            val a = context.theme.obtainStyledAttributes(attrs, R.styleable.BalanceTextView, 0, 0)
            try {
                primaryColor = a.getResourceId(R.styleable.BalanceTextView_primary_color, R.color.colorText)
                secondaryColor = a.getResourceId(R.styleable.BalanceTextView_secondary_color, R.color.colorTextSecondary)
                separator = Separator.valueOf(a.getInt(R.styleable.BalanceTextView_separator, Separator.COMMA.key))
            } finally {
                a.recycle()
            }
        }
    }

    override fun setText(text: CharSequence?, type: BufferType?) {
        val value = when (text.isNullOrBlank() || separator == Separator.UNDEFINED) {
            true -> text
            false -> formatText(text.toString())
        }

        super.setText(value, type)
    }

    private fun formatText(text: String): SpannableStringBuilder {
        val separatorText = separator.value
        val primaryText = text.substringBefore(separatorText).toRubValue()
        val secondaryText = text.substringAfter(separatorText)

        return SpannableStringBuilder()
            .append(primaryText, primaryColor)
            .append(separatorText.toString(), secondaryColor)
            .append(secondaryText, secondaryColor)
    }

    private fun SpannableStringBuilder.append(text: String, @ColorRes textColorRes: Int): SpannableStringBuilder {
        val index = length
        val colorSpan = ForegroundColorSpan(color(textColorRes))
        append(text)
        setSpan(colorSpan, index, index + text.length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        return this
    }

    enum class Separator(val key: Int, val value: Char) {
        DOT(0, '.'),
        COMMA(1, ','),
        UNDEFINED(2, ' ');

        companion object {

            fun valueOf(key: Int) = values().firstOrNull { it.key == key } ?: COMMA
        }
    }
}
