package com.usacheow.coreui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.usacheow.coreui.adapters.base.Populatable
import com.usacheow.coreui.adapters.base.ViewType

class ViewTypesAdapter(
    private var entities: List<ViewType> = emptyList()
) : RecyclerView.Adapter<ViewTypesAdapter.ViewTypesViewHolder>() {

    var onPaginationAction: (() -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewTypesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return ViewTypesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewTypesViewHolder, position: Int) {
        holder.populate(entities[position])
        if (itemCount - position == 10) {
            onPaginationAction?.invoke()
            onPaginationAction = null
        }
    }

    override fun getItemCount(): Int {
        return entities.size
    }

    override fun getItemViewType(position: Int) = entities[position].layoutId

    fun update(list: List<ViewType>) {
        entities = list
        notifyDataSetChanged()
    }

    fun getData() = entities

    class ViewTypesViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView), Populatable<ViewType> {

        override fun populate(model: ViewType) {
            (itemView as Populatable<ViewType>).populate(model)
        }
    }

}