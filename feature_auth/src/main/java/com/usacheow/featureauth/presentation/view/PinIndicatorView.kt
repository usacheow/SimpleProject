package com.usacheow.featureauth.presentation.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.usacheow.coreui.utils.view.color
import com.usacheow.coreui.utils.view.toPx
import com.usacheow.featureauth.R
import kotlin.math.min

private const val DEFAULT_RADIUS_DP = 10
private const val DOT_MARGIN = 16
private const val PIN_CODE_LENGTH = 4

class PinIndicatorView
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : View(context, attrs, defStyleAttr) {

    private val defaultPaint by lazy {
        Paint().apply {
            style = Paint.Style.FILL
            color = color(defaultColor)
            isAntiAlias = true
        }
    }
    private val inputtedPaint by lazy {
        Paint().apply {
            style = Paint.Style.FILL
            color = color(selectedColor)
            isAntiAlias = true
        }
    }

    private val diameterWithMargin = DOT_MARGIN.toPx + DEFAULT_RADIUS_DP.toPx * 2
    private val errorColor = R.color.error
    private val defaultColor = R.color.black_10
    private val selectedColor = R.color.colorAccent

    private var firstDotX = paddingLeft + DEFAULT_RADIUS_DP.toPx.toFloat()
    private var firstDotY = paddingTop + DEFAULT_RADIUS_DP.toPx.toFloat()
    private var inputtedCounter = 0

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        fun draw(x: Float, paint: Paint) {
            canvas.drawCircle(x, firstDotY, DEFAULT_RADIUS_DP.toPx.toFloat(), paint)
        }

        var paint = when (inputtedCounter > 0) {
            true -> inputtedPaint
            false -> defaultPaint
        }
        draw(firstDotX, paint)

        for (i in 1 until PIN_CODE_LENGTH) {
            paint = when (inputtedCounter > i) {
                true -> inputtedPaint
                false -> defaultPaint
            }
            draw(firstDotX + i * diameterWithMargin, paint)
        }
    }

    fun increaseCounter() {
        inputtedCounter++
        invalidate()
    }

    fun decreaseCounter() {
        inputtedCounter--
        invalidate()
    }

    fun indicateError() {
        inputtedCounter = 0
        defaultPaint.color = color(errorColor)
        invalidate()
    }

    fun refreshPinView() {
        inputtedCounter = 0
        defaultPaint.color = color(defaultColor)
        invalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec))
    }

    private fun measureWidth(measureSpec: Int): Int {
        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)

        var result: Int
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize
        } else {
            val count = PIN_CODE_LENGTH
            result = (paddingLeft + paddingRight + count * 2 * DEFAULT_RADIUS_DP.toPx + 3 * DOT_MARGIN.toPx)
            if (specMode == MeasureSpec.AT_MOST) {
                result = min(result, specSize)
            }
        }
        return result
    }

    private fun measureHeight(measureSpec: Int): Int {
        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)

        var result: Int
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize
        } else {
            result = (paddingBottom + paddingTop + 2 * DEFAULT_RADIUS_DP.toPx)
            if (specMode == MeasureSpec.AT_MOST) {
                result = min(result, specSize)
            }
        }
        return result
    }
}