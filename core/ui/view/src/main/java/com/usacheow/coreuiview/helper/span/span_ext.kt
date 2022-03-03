package com.usacheow.coreuiview.helper.span

import android.text.Annotation
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ClickableSpan
import android.view.View

/*
<string name="text_res">Some <annotation name="annotationName">text</annotation></string>

getText(R.string.text_res).replaceAnnotationsWithSpans {
    if (it.value == "annotationName") arrayOf(clickableSpan { })
    else emptyArray()
}
* */

/*
inSpans(
    TextAppearanceSpan(context, CoreUiR.style.Simple_Text_Body_S),
    ForegroundColorSpan(resources.getColor(CoreUiR.color.white_60)),
)
* */
fun CharSequence.replaceAnnotationsWithSpans(annotationToSpans: (Annotation) -> Array<out Any>): SpannableString {
    val spannableString = SpannableString(this)
    val annotations = spannableString.getSpans(0, length, Annotation::class.java)

    for (annotation in annotations) {
        annotationToSpans(annotation).forEach { span ->
            spannableString.setSpan(
                span,
                spannableString.getSpanStart(annotation),
                spannableString.getSpanEnd(annotation),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
    }
    return spannableString
}

fun clickableSpan(onClick: (widget: View) -> Unit) = object : ClickableSpan() {
    override fun onClick(widget: View) {
        onClick(widget)
    }
}