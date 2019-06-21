package com.kapmayn.coreuikit.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kapmayn.coreuikit.base.IClickable
import com.kapmayn.coreuikit.base.IPopulatable

open class PopulateAdapter<MODEL>(
    private val layoutResId: Int,
    private val models: MutableList<MODEL> = mutableListOf(),
    var clickListener: (MODEL) -> Unit = {}
) : RecyclerView.Adapter<PopulateAdapter.PopulateViewHolder<MODEL>>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): PopulateViewHolder<MODEL> {
        val view = LayoutInflater.from(viewGroup.context).inflate(layoutResId, viewGroup, false)

        return PopulateViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: PopulateViewHolder<MODEL>, position: Int) {
        val data = models[position]
        viewHolder.populate(data, clickListener)
    }

    override fun getItemCount() = models.size

    fun updateData(items: List<MODEL>) {
        models.clear()
        models += items.toMutableList()
        notifyDataSetChanged()
    }

    fun removeItem(item: MODEL) {
        val position = models.indexOf(item)

        models.remove(item)
        notifyItemRemoved(position)
    }

    class PopulateViewHolder<MODEL>(val view: View) : RecyclerView.ViewHolder(view) {

        fun populate(model: MODEL, clickListener: (MODEL) -> Unit) {
            val populateView = view as IPopulatable<MODEL>
            populateView.populate(model)

            if (view is IClickable) {
                val clickableView = view as IClickable
                clickableView.setListener { clickListener(model) }
            } else {
                view.setOnClickListener { clickListener(model) }
            }
        }
    }
}