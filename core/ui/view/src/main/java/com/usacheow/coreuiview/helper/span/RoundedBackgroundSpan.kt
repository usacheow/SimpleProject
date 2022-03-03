package com.usacheow.coreuiview.helper.span

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.text.style.ReplacementSpan
import com.usacheow.coreuiview.helper.toPx

class RoundedBackgroundSpan(
    private val textColor: Int,
    private val backgroundColor: Int
) : ReplacementSpan() {

    private val paddingHorizontal = 8.toPx.toFloat()
    private val paddingVertical = 4.toPx.toFloat()
    private val cornerRadius = 16.toPx.toFloat()

    override fun draw(
        canvas: Canvas,
        text: CharSequence,
        start: Int,
        end: Int,
        x: Float,
        top: Int,
        y: Int,
        bottom: Int,
        paint: Paint
    ) {
        val newTop = y + paint.fontMetrics.ascent - paddingVertical
        val newBottom = y + paint.fontMetrics.descent + paddingVertical
        val rect = RectF(x, newTop, x + measureText(paint, text, start, end) + 2 * paddingHorizontal, newBottom)
        paint.color = backgroundColor

        canvas.drawRoundRect(rect, cornerRadius, cornerRadius, paint)
        paint.color = textColor
        canvas.drawText(text, start, end, x + paddingHorizontal, y.toFloat(), paint)
    }

    override fun getSize(paint: Paint, text: CharSequence?, start: Int, end: Int, fm: Paint.FontMetricsInt?): Int {
        return (paint.measureText(text, start, end) + 2 * paddingHorizontal).toInt()
    }

    private fun measureText(paint: Paint, text: CharSequence, start: Int, end: Int): Float {
        return paint.measureText(text, start, end)
    }
}