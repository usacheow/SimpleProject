package com.usacheow.coreui.uikit.helper

import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.usacheow.core.resource.TextSource
import java.lang.ref.WeakReference
import com.usacheow.core.R as CoreR

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

    if (source == null) {
        makeGone()
    } else {
        populate(source.toCharSequence(context))
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
    expandLinkText: String = string(CoreR.string.collapse_text),
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