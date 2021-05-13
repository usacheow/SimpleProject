package com.usacheow.coreui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.usacheow.coreui.adapter.base.Populatable
import com.usacheow.coreui.adapter.base.ViewType

open class ViewTypesAdapter(
    private var entities: List<ViewType> = emptyList()
) : RecyclerView.Adapter<ViewTypesAdapter.ViewTypesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewTypesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return ViewTypesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewTypesViewHolder, position: Int) {
        holder.populate(entities[getIndexByPosition(position)])
    }

    override fun getItemCount(): Int {
        return entities.size
    }

    override fun getItemViewType(position: Int) = entities[getIndexByPosition(position)].layoutId

    fun update(list: List<ViewType>) {
        entities = list
        notifyDataSetChanged()
    }

    fun getData() = entities

    protected open fun getIndexByPosition(position: Int) = position

    class ViewTypesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), Populatable<ViewType> {

        override fun populate(model: ViewType) {
            (itemView as? Populatable<ViewType>)?.populate(model)
        }
    }
}