package com.usacheow.coreui.utils.view

import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.text.HtmlCompat
import com.usacheow.core.TextSource
import com.usacheow.core.resource.ResourcesWrapper
import com.usacheow.coreui.R as CoreUiR
import java.lang.ref.WeakReference

private const val TEXT_ANIMATION_DURATION = 200L

fun TextView.populate(source: TextSource?) {
    fun TextView.populate(s: CharSequence) {
        if (s.isEmpty()) {
            makeGone()
        } else {
            text = s
            makeVisible()
        }
    }

    when (source) {
        null -> makeGone()

        is TextSource.Simple -> populate(source.text)

        is TextSource.Spanned -> populate(source.text)

        is TextSource.Html -> {
            text = HtmlCompat.fromHtml(source.html, HtmlCompat.FROM_HTML_MODE_LEGACY)
            makeVisible()
        }

        is TextSource.Res -> {
            text = string(source.res)
            makeVisible()
        }

        is TextSource.Plural -> {
            text = resources.getQuantityText(source.res, source.quantity)
            makeVisible()
        }
    }
}

fun TextView.makeExpandable(
    value: String,
    canCollapse: Boolean,
    container: WeakReference<ViewGroup>,
    dividerText: String = "... ",
    expandLinkText: String = "Ещё",
) {
    container.get()?.doWithTransition(TEXT_ANIMATION_DURATION) {
        movementMethod = LinkMovementMethod.getInstance()
        highlightColor = Color.TRANSPARENT

        var finalText = value.substring(0, 20) + dividerText
        val startIndex = finalText.length
        val endIndex = startIndex + expandLinkText.length
        finalText += expandLinkText

        text = SpannableString(finalText).apply {
            val click = SimpleClickableSpan {
                when {
                    canCollapse -> makeCollapsable(value, container)
                    else -> container.get()?.doWithTransition(200) { text = value }
                }
            }
            setSpan(click, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
    }
}

private fun TextView.makeCollapsable(
    value: String,
    container: WeakReference<ViewGroup>,
    dividerText: String = " ",
    expandLinkText: String = string(CoreUiR.string.collapse_text),
) {
    container.get()?.doWithTransition(TEXT_ANIMATION_DURATION) {
        var finalText = value + dividerText
        val startIndex = finalText.length
        val endIndex = startIndex + expandLinkText.length
        finalText += expandLinkText

        text = SpannableString(finalText).apply {
            val click = SimpleClickableSpan {
                makeExpandable(value, true, container)
            }
            setSpan(click, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
    }
}

class SimpleClickableSpan(private val onClick: () -> Unit) : ClickableSpan() {
    override fun onClick(widget: View) {
        onClick()
    }

    override fun updateDrawState(ds: TextPaint) {
        super.updateDrawState(ds)
        ds.isUnderlineText = false
    }
}