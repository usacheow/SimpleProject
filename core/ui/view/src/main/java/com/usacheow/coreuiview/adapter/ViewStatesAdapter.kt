package com.usacheow.coreuiview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.usacheow.coreuiview.tools.Populatable
import com.usacheow.coreuiview.tools.ViewState

open class ViewStateAdapter(
    private var entities: List<ViewState> = emptyList()
) : RecyclerView.Adapter<ViewTypesViewHolder<ViewState>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewTypesViewHolder<ViewState> {
        val itemView = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return ViewTypesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewTypesViewHolder<ViewState>, position: Int) {
        holder.populate(entities[getIndexByPosition(position)])
    }

    override fun getItemCount(): Int {
        return entities.size
    }

    override fun getItemViewType(position: Int) = entities[getIndexByPosition(position)].layoutId

    fun update(list: List<ViewState>) {
        entities = list.toMutableList()
        notifyDataSetChanged()
    }

    fun update(item: ViewState, position: Int) {
        entities = entities.toMutableList().apply { this[position] = item }
        notifyItemChanged(position)
    }

    fun getData() = entities.toList()

    protected open fun getIndexByPosition(position: Int) = position
}

class ViewTypesViewHolder<MODEL>(itemView: View) : RecyclerView.ViewHolder(itemView), Populatable<MODEL> {

    override fun populate(model: MODEL) {
        (itemView as? Populatable<MODEL>)?.populate(model)
    }
}