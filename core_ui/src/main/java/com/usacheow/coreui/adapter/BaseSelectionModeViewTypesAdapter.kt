package com.usacheow.coreui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.usacheow.coreui.adapter.base.Populatable
import com.usacheow.coreui.adapter.base.TagViewType

abstract class BaseSelectionModeViewTypesAdapter(
    private var entities: List<TagViewType> = emptyList()
) : RecyclerView.Adapter<BaseSelectionModeViewTypesAdapter.BaseSelectionModeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseSelectionModeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return BaseSelectionModeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BaseSelectionModeViewHolder, position: Int) {
        holder.populate(entities[position])
    }

    override fun getItemCount() = entities.size

    override fun getItemViewType(position: Int) = entities[position].layoutId

    fun getData() = entities

    class BaseSelectionModeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun populate(model: TagViewType) {
            (itemView as Populatable<TagViewType>).populate(model)
        }
    }

}