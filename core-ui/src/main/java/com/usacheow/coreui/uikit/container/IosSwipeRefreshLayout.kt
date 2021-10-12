package com.usacheow.coreui.uikit.container

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.animation.addListener
import androidx.core.view.NestedScrollingParentHelper
import androidx.core.view.ScrollingView
import androidx.core.view.ViewCompat
import androidx.core.view.children
import androidx.core.view.marginTop
import com.usacheow.coreui.R as CoreUiR
import com.usacheow.coreui.uikit.helper.EmptyAnimationListener
import com.usacheow.coreui.uikit.helper.drawable
import com.usacheow.coreui.uikit.helper.toPx
import kotlin.math.min

private const val REFRESH_FADE_IN_PROGRESS = 0.4F
private const val REFRESH_APPLY_PROGRESS = 0.6F
private const val MIN_PROGRESS = 0F
private const val MAX_PROGRESS = 1F

const val UNDEFINED = -1

private const val SCROLL_DISTANCE_COEFFICIENT = 2
private const val TARGET_ROTATION = 360F
private const val INITIAL_ROTATION = 0F

private const val INITIAL_LOADING_DURATION = 2000L
private const val ROTATION_VELOCITY = 0.35F
private const val VELOCITY = 2.5F
private const val SCROLL_UP = 1

class IOSSwipeRefreshLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : FrameLayout(context, attrs) {

    private var swipeRefreshProgressCallback: ((progress: Float) -> Unit)? = null
    private var onRefreshCallback: (() -> Unit)? = null
    private var hasChildContainerReachedTop: (() -> Boolean)? = null

    private var isBeingDragged = false
    private var lastY = 0
    private var initialDownY = 0
    private var initialMotionY = 0
    private var pointerIndex = -1
    private var activePointerId = -1

    private val refreshIconWidth by lazy { 32.toPx }
    private val refreshIconHeight by lazy { 32.toPx }
    private val refreshIconMarginVertical by lazy { 20.toPx }
    private val refreshIconView by lazy { createProgressView() }
    private val refreshIconAlphaFunction = LinearFunction(
        LinearFunction.Point(REFRESH_FADE_IN_PROGRESS, MIN_PROGRESS),
        LinearFunction.Point(REFRESH_APPLY_PROGRESS, MAX_PROGRESS)
    )

    private val minScrollDistance = (2 * refreshIconMarginVertical + refreshIconHeight) * SCROLL_DISTANCE_COEFFICIENT
    private val totalScrollDistance by lazy { min(192.toPx, minScrollDistance) }

    private val touchSlop = ViewConfiguration.get(context).scaledTouchSlop
    private val loadingDuration = INITIAL_LOADING_DURATION
    private val velocity = VELOCITY / context.resources.displayMetrics.density
    private val rotationVelocity = ROTATION_VELOCITY
    private val refreshIconAdditionalTranslation = 48.toPx

    private var currentContainerOffset: Int = 0

    private var isScrollConsuming = false
    private var scrollableChild: View? = null

    private val internalHierarchyChangeListener = InternalOnHierarchyChangeListener()
    private val nestedScrollingParentHelper = NestedScrollingParentHelper(this)

    private var touchToIdleAnimator: Animator? = null
    private var touchLoadingIdleAnimatorSet: AnimatorSet? = null

    private val rotationAnimation = object : Animation() {
        var initialRotation = INITIAL_ROTATION
            set(value) {
                field = value
                endRotation = value + TARGET_ROTATION
            }
        private var endRotation = TARGET_ROTATION

        override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
            refreshIconView.rotation = interpolatedTime * endRotation
        }
    }.apply {
        repeatCount = Animation.INFINITE
        duration = (TARGET_ROTATION / rotationVelocity).toLong()

        setAnimationListener(object : EmptyAnimationListener() {
            override fun onAnimationStart(animation: Animation?) {
                initialRotation = refreshIconView.rotation
            }
        })
    }

    init {
        refreshIconView.let {
            addView(it)
            it.addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ -> updateRefreshIconPosition() }
        }

        context.theme.obtainStyledAttributes(attrs, CoreUiR.styleable.IosSwipeRefreshLayout, 0, 0)
            .apply {
                val refreshIcon = getDrawable(CoreUiR.styleable.IosSwipeRefreshLayout_android_icon)
                    ?: drawable(CoreUiR.drawable.ic_refresh_default)
                refreshIconView.setImageDrawable(refreshIcon)
            }
            .recycle()

        super.setOnHierarchyChangeListener(internalHierarchyChangeListener)
        internalHierarchyChangeListener.onHierarchyChanged = ::findScrollableContainer
    }

    fun hasChildContainerReachedTop(callback: (() -> Boolean)) = apply {
        hasChildContainerReachedTop = callback
    }

    fun bindWith(motionLayout: MotionLayout) = hasChildContainerReachedTop {
        motionLayout.progress == 0f
    }

    fun onRefreshCallback(callback: () -> Unit) = apply {
        onRefreshCallback = callback
    }

    fun swipeRefreshProgressCallback(callback: (progress: Float) -> Unit) = apply {
        swipeRefreshProgressCallback = callback
    }

    override fun setOnHierarchyChangeListener(listener: OnHierarchyChangeListener?) {
        internalHierarchyChangeListener.hierarchyChangeListener = listener
    }

    override fun onStartNestedScroll(child: View, target: View, axes: Int): Boolean {
        return isEnabled &&
            !isAnimationRunning() &&
            (axes and ViewCompat.SCROLL_AXIS_VERTICAL) != 0 &&
            canScrollDown()
    }

    override fun onNestedScrollAccepted(child: View, target: View, axes: Int) {
        refreshIconView.bringToFront()
        nestedScrollingParentHelper.onNestedScrollAccepted(child, target, axes)
    }

    override fun onStopNestedScroll(target: View) {
        onTouchStop()
        nestedScrollingParentHelper.onStopNestedScroll(target)
    }

    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray) {
        if (dy > 0) {
            // игнорируем consumedScroll, чтобы перехватить скролл.
            consumed[1] = dy
        }

        scrollableChild?.let { nonNullView ->
            isScrollConsuming = true
            updateState(nonNullView, currentContainerOffset, totalScrollDistance, AnimationType.COMMON)
        }
    }

    override fun onNestedPreFling(target: View?, velocityX: Float, velocityY: Float): Boolean {
        if (isScrollConsuming) {
            return true
        }

        return super.onNestedPreFling(target, velocityX, velocityY)
    }

    override fun getNestedScrollAxes(): Int {
        return nestedScrollingParentHelper.nestedScrollAxes
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        if (shouldSkipTouchEvent()) {
            return false
        }

        when (ev.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                isBeingDragged = false
                activePointerId = ev.getPointerId(0)
                pointerIndex = ev.findPointerIndex(activePointerId)

                if (pointerIndex < 0) {
                    return false
                }

                initialDownY = ev.getY(pointerIndex)
                    .toInt()
            }

            MotionEvent.ACTION_MOVE -> {
                if (activePointerId == UNDEFINED) {
                    return false
                }

                pointerIndex = ev.findPointerIndex(activePointerId)

                if (pointerIndex < 0) {
                    return false
                }

                startDragging(ev.getY(pointerIndex))
            }

            MotionEvent.ACTION_POINTER_UP -> {
                onSecondaryPointerUp(ev)
            }

            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                isBeingDragged = false
                activePointerId = UNDEFINED
            }
        }

        return isBeingDragged
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        pointerIndex = UNDEFINED

        if (shouldSkipTouchEvent()) {
            return false
        }

        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                activePointerId = event.getPointerId(0)
                isBeingDragged = false
            }

            MotionEvent.ACTION_MOVE -> {
                pointerIndex = event.findPointerIndex(activePointerId)

                if (pointerIndex < 0) {
                    return false
                }

                val y = event.getY(pointerIndex)
                val offset = (lastY - y).toInt()
                consumeScrollPortion(offset)
                scrollableChild?.let { child ->
                    updateState(child, currentContainerOffset, totalScrollDistance, AnimationType.COMMON)
                }

                lastY = y.toInt()
            }

            MotionEvent.ACTION_POINTER_DOWN -> {
                pointerIndex = event.getActionIndex()
                if (pointerIndex < 0) {
                    return false
                }
                activePointerId = event.getPointerId(pointerIndex)
            }

            MotionEvent.ACTION_POINTER_UP -> {
                onSecondaryPointerUp(event)
            }

            MotionEvent.ACTION_UP -> {
                pointerIndex = event.findPointerIndex(activePointerId)
                if (pointerIndex < 0) {
                    return false
                }

                if (isBeingDragged) {
                    isBeingDragged = false
                    onTouchStop()
                }
                activePointerId = UNDEFINED

                return false
            }

            MotionEvent.ACTION_CANCEL -> {
                return false
            }
        }

        return true
    }

    private fun shouldSkipTouchEvent(): Boolean {
        return !isEnabled || isScrollConsuming || isAnimationRunning() || !hasChildReachTop()
    }

    private fun startDragging(positionY: Float) {
        refreshIconView.bringToFront()

        val yDiff: Float = positionY - initialDownY
        if (yDiff > touchSlop && !isBeingDragged) {
            isBeingDragged = true
            initialMotionY = initialDownY + touchSlop
            lastY = initialMotionY
        }
    }

    private fun onSecondaryPointerUp(ev: MotionEvent) {
        val pointerIndex = ev.actionIndex
        val pointerId = ev.getPointerId(pointerIndex)
        if (pointerId == activePointerId) {
            val newPointerIndex = if (pointerIndex == 0) 1 else 0
            activePointerId = ev.getPointerId(newPointerIndex)
        }
    }

    private fun canScrollDown(): Boolean = scrollableChild?.canScrollVertically(SCROLL_UP) ?: false

    private fun hasChildReachTop(): Boolean {
        return hasChildContainerReachedTop?.invoke()
            ?: scrollableChild?.let { if (it is ScrollingView) it.computeVerticalScrollOffset() == 0 else false }
            ?: false
    }

    private fun updateState(
        container: View,
        translationYPosition: Int,
        totalDistance: Int,
        animationType: AnimationType,
    ) {
        updateContainerOffset(container, translationYPosition)
        updateRefreshIcon(translationYPosition, totalDistance, animationType)
    }

    private fun updateContainerOffset(container: View, translationYPosition: Int) {
        container.translationY = translationYPosition.toFloat()
    }

    private fun consumeScrollPortion(scroll: Int): Int {
        if (!hasChildReachTop()) {
            return 0
        }

        if (scroll < 0 && currentContainerOffset <= totalScrollDistance && hasChildReachTop()) {
            val availableConsumeDistance = totalScrollDistance - currentContainerOffset

            val couldConsume = (availableConsumeDistance + scroll) >= 0
            val consume = if (couldConsume) {
                scroll
            } else {
                -availableConsumeDistance
            }

            currentContainerOffset -= consume

            return consume
        }

        if (scroll > 0 && currentContainerOffset > 0) {
            val availableConsumeDistance = currentContainerOffset
            val couldConsume = availableConsumeDistance - scroll >= 0
            val consume = if (couldConsume) {
                scroll
            } else {
                availableConsumeDistance
            }
            currentContainerOffset -= consume

            return consume
        }

        return 0
    }

    private fun findScrollableContainer() {
        scrollableChild = children.find { it is ScrollingView || it is MotionLayout } ?: children.toList()
            .filterIsInstance<ViewGroup>()
            .firstOrNull()
            ?.children
            ?.find { it is ScrollingView || it is MotionLayout }
    }

    private fun updateRefreshIconPosition() {
        refreshIconView.translationX = ((width / 2) - (refreshIconView.width / 2)).toFloat()
    }

    private fun onTouchStop() {
        if (currentContainerOffset <= 0) {
            return
        }

        rotationAnimation.cancel()
        rotationAnimation.reset()

        touchToIdleAnimator?.cancel()
        touchLoadingIdleAnimatorSet?.cancel()

        if (shouldInvokeRefreshCallback()) {
            onRefreshCallback?.invoke()
            moveToState(true)
        } else {
            moveToState(false)
        }
    }

    private fun moveToState(withLoading: Boolean) {
        if (currentContainerOffset < getTargetEnd()) {
            return
        }

        if (withLoading) {
            val loadingTarget = loadingEndTargetPosition()

            val touchToLoadingAnimator = createAnimator(
                animationType = AnimationType.LOADING,
                start = currentContainerOffset,
                end = loadingTarget,
                velocity = velocity
            )

            val loadingToIdleAnimator = createAnimator(
                animationType = AnimationType.COMMON,
                start = loadingTarget,
                end = getTargetEnd(),
                velocity = velocity
            ).apply { startDelay = loadingDuration }

            touchLoadingIdleAnimatorSet = AnimatorSet()
                .apply {
                    playSequentially(touchToLoadingAnimator, loadingToIdleAnimator)
                    addListener(onEnd = { resetState() }, onCancel = { resetState() })
                    start()
                }

            rotationAnimation.duration = (TARGET_ROTATION / rotationVelocity).toLong()
            refreshIconView.startAnimation(rotationAnimation)
        } else {
            touchToIdleAnimator = createAnimator(
                animationType = AnimationType.COMMON,
                start = currentContainerOffset,
                end = getTargetEnd(),
                velocity = velocity
            ).apply {
                addListener(onEnd = { resetState() }, onCancel = { resetState() })
                start()
            }
        }
    }

    private fun loadingEndTargetPosition() =
        (scrollableChild?.marginTop ?: 0) + refreshIconHeight + 2 * refreshIconMarginVertical

    private fun shouldInvokeRefreshCallback(): Boolean {
        return currentContainerOffset.toFloat() / totalScrollDistance >= REFRESH_APPLY_PROGRESS
    }

    private fun createProgressView() = AppCompatImageView(context).apply {
        layoutParams = LayoutParams(refreshIconWidth, refreshIconHeight)
        alpha = 0f
    }

    private fun updateRefreshIcon(offsetDistance: Int, totalDistance: Int, animationType: AnimationType) {
        val progress = offsetDistance.toFloat() / totalDistance
        swipeRefreshProgressCallback?.invoke(progress)

        when (animationType) {
            AnimationType.COMMON -> updateRefreshIconByTouch(progress, offsetDistance)
            AnimationType.LOADING -> updateRefreshIconByAnimation(offsetDistance)
        }
    }

    private fun updateRefreshIconByTouch(progress: Float, offsetDistance: Int) {
        val refreshProgress = ((progress - REFRESH_FADE_IN_PROGRESS) * 2)
        val alphaProgress = refreshIconAlphaFunction.calculateY(progress)
        val correctAlpha = maxOf(MIN_PROGRESS, minOf(MAX_PROGRESS, alphaProgress))
        refreshIconView.apply {
            alpha = correctAlpha
            rotation = (refreshProgress * TARGET_ROTATION)
            translationY = getRefreshIconPosition(offsetDistance)
        }
    }

    private fun updateRefreshIconByAnimation(offsetDistance: Int) {
        refreshIconView.translationY = getRefreshIconPosition(offsetDistance)
    }

    private fun getRefreshIconPosition(offsetDistance: Int): Float = offsetDistance +
        (scrollableChild?.marginTop ?: 0) -
        (refreshIconView.height + refreshIconMarginVertical) +
        refreshIconAdditionalTranslation.toFloat()

    private fun isAnimationRunning(): Boolean {
        return touchLoadingIdleAnimatorSet?.isStarted == true || touchToIdleAnimator?.isStarted == true
    }

    private fun createAnimator(animationType: AnimationType, start: Int, end: Int, velocity: Float): Animator {
        return ValueAnimator.ofFloat(MAX_PROGRESS, MIN_PROGRESS)
            .apply {
                addUpdateListener { animator ->
                    val value = (animator.animatedValue as Float)
                    val translationYPosition = (start * value - end * value + end).toInt()
                    scrollableChild?.let {
                        updateState(it, translationYPosition, totalScrollDistance, animationType)
                    }
                }
                this.duration = ((start - end) / velocity).toLong()
            }
    }

    private fun resetState() {
        isScrollConsuming = false
        currentContainerOffset = 0
        rotationAnimation.cancel()
        rotationAnimation.reset()
    }

    private fun getTargetEnd(): Int {
        return scrollableChild?.marginTop ?: 0
    }

    private enum class AnimationType {
        COMMON,
        LOADING
    }
}

private class InternalOnHierarchyChangeListener : ViewGroup.OnHierarchyChangeListener {

    var hierarchyChangeListener: ViewGroup.OnHierarchyChangeListener? = null

    var onHierarchyChanged: (() -> Unit)? = null

    override fun onChildViewRemoved(parent: View?, child: View?) {
        onHierarchyChanged?.invoke()
        hierarchyChangeListener?.onChildViewRemoved(parent, child)
    }

    override fun onChildViewAdded(parent: View?, child: View?) {
        onHierarchyChanged?.invoke()
        hierarchyChangeListener?.onChildViewAdded(parent, child)
    }
}

private class LinearFunction(
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