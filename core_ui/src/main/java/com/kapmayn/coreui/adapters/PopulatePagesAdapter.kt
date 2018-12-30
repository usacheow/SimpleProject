package com.kapmayn.coreui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.kapmayn.core.base.IPopulatable

class PopulatePagesAdapter<T>(
    private val layoutResId: Int,
    private val models: MutableList<T> = mutableListOf(),
    var clickListener: ((T) -> Unit)? = null
) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(container.context)
        val item = inflater.inflate(layoutResId, container, false)
        val data = models[position]

        (item as IPopulatable<T>).populate(data)
        item.setOnClickListener { clickListener?.invoke(data) }

        container.addView(item)
        return item
    }

    override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
        collection.removeView(view as View)
    }

    override fun getCount(): Int {
        return models.size
    }

    override fun isViewFromObject(view: View, any: Any): Boolean {
        return view === any
    }

    override fun getPageTitle(position: Int): CharSequence {
        return models[position].toString()
    }

    fun updateData(items: List<T>) {
        models.clear()
        models += items.toMutableList()
        notifyDataSetChanged()
    }
}