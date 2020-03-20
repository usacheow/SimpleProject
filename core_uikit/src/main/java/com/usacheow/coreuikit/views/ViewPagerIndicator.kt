package com.usacheow.coreuikit.views

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.StateListDrawable
import android.util.AttributeSet
import android.util.StateSet
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager.widget.ViewPager
import com.usacheow.coreuikit.R
import com.usacheow.coreuikit.utils.ext.onPageSelected

private const val DEFAULT_DELIMITER_SIZE = 10
private const val DEFAULT_ITEM_HEIGHT = 10

open class ViewPagerIndicator
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var delimiterSize: Int = DEFAULT_DELIMITER_SIZE
    private val itemHeight: Int
    private val itemColor: Int
    private val itemSelectedColor: Int
    private lateinit var viewPager: ViewPager
    private val indexImages = mutableListOf<ImageView>()
    private var lastSelectedIndex: Int = 0
    protected var pagesCount: Int = 0

    init {
        orientation = LinearLayout.HORIZONTAL
        val attributes = context.theme.obtainStyledAttributes(attrs, R.styleable.ViewPagerIndicator, 0, 0)

        try {
            delimiterSize = attributes.getDimensionPixelSize(R.styleable.ViewPagerIndicator_item_margin, DEFAULT_DELIMITER_SIZE)
            itemHeight = attributes.getDimensionPixelSize(R.styleable.ViewPagerIndicator_item_height, DEFAULT_ITEM_HEIGHT)
            itemColor = attributes.getColor(R.styleable.ViewPagerIndicator_item_color, Color.WHITE)
            itemSelectedColor = attributes.getColor(R.styleable.ViewPagerIndicator_item_selected_color, Color.GRAY)
        } finally {
            attributes.recycle()
        }
    }

    fun setupWithViewPager(viewPager: ViewPager, count: Int = viewPager.adapter!!.count) {
        this.viewPager = viewPager
        setCount(count)
        lastSelectedIndex = 0
        updateSelectedItem(0)
        viewPager.onPageSelected { updateSelectedItem(it) }
        if (pagesCount == 0)
            visibility = View.GONE
    }

    fun update() {
        val count = viewPager.adapter!!.count
        if (count == 0) {
            visibility = View.GONE
            return
        }

        setCount(count)
        if (indexImages.size <= lastSelectedIndex) {
            lastSelectedIndex = indexImages.size - 1
        }
        indexImages[lastSelectedIndex].isActivated = true
    }

    protected open fun updateSelectedItem(selectedIndex: Int) {
        if (selectedIndex in 0 until pagesCount) {
            indexImages[lastSelectedIndex].isActivated = false
            indexImages[selectedIndex].isActivated = true
            lastSelectedIndex = selectedIndex
        }
    }

    protected open fun setCount(pageCount: Int) {
        this.pagesCount = pageCount
        removeAllViews()
        indexImages.clear()

        for (i in 0 until pageCount) {
            addView(createBoxedItem(i))
        }
    }

    private fun createBoxedItem(position: Int): ImageView {
        val boxParams = LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, itemHeight).apply {
            weight = 1f
            if (position > 0) {
                setMargins(delimiterSize, 0, 0, 0)
            }
        }
        val box = ImageView(context).apply {
            val drawable = StateListDrawable()
            drawable.addState(intArrayOf(android.R.attr.state_activated), ColorDrawable(itemSelectedColor))
            drawable.addState(StateSet.WILD_CARD, ColorDrawable(itemColor))
            setImageDrawable(drawable)
            isActivated = false
            layoutParams = boxParams
        }
        indexImages.add(box)
        return box
    }
}
