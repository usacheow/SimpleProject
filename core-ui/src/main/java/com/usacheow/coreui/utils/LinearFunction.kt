package com.usacheow.coreui.utils

class LinearFunction(
    val point1: Point,
    val point2: Point
) {
    private val k: Float
    private val m: Float

    init {
        val y1 = point1.y
        val x1 = point1.x
        val y2 = point2.y
        val x2 = point2.x

        k = if (x2 - x1 == 0F) {
            0F
        } else {
            (y2 - y1) / (x2 - x1)
        }

        m = y1 - k * x1
    }

    fun calculateY(x: Float): Float {
        // y = kx + m
        return k * x + m
    }

    fun calculateX(y: Float): Float {
        // x = (y - m) / k
        return if (k == 0f) {
            m
        } else {
            (y - m) / k
        }
    }

    class Point(val x: Float, val y: Float)
}