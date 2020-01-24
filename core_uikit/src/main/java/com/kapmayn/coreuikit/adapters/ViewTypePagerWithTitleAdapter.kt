package com.kapmayn.coreuikit.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.kapmayn.coreuikit.base.Populatable
import com.kapmayn.coreuikit.base.ViewType

open class ViewTypePagerWithTitleAdapter(
    val models: List<Pair<String, ViewType>>
) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val item = models[position]
        val view = LayoutInflater.from(container.context)
            .inflate(item.second.layoutId, container, false) as Populatable<ViewType>

        view.populate(item.second)

        container.addView(view as View)
        return view
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return models[position].first
    }

    override fun getItemPosition(item: Any) = if (models.contains(item)) {
        models.indexOf(item)
    } else {
        POSITION_NONE
    }

    override fun getCount() = models.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}