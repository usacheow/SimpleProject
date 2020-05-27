package com.usacheow.coreuikit.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.usacheow.coreuikit.base.Populatable
import com.usacheow.coreuikit.base.RadioViewType

class RadioActionItemAdapter(
    private val entities: List<RadioViewType> = emptyList()
) : RecyclerView.Adapter<RadioActionItemAdapter.RadioActionItemHolder>() {

    private var selectedItemPosition: Int? = null

    init {
        selectedItemPosition = entities.indexOfFirst {
            it.isSelected
        }
        if (selectedItemPosition == -1) {
            selectedItemPosition = null
        }

        entities.forEachIndexed { index, actionItem ->
            if (index != selectedItemPosition) {
                actionItem.isSelected = false
            }
            actionItem.onSelectAction = { onSelected(index) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RadioActionItemHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return RadioActionItemHolder(itemView)
    }

    override fun onBindViewHolder(holder: RadioActionItemHolder, position: Int) {
        holder.populate(entities[position])
    }

    private fun onSelected(position: Int) {
        selectedItemPosition?.let {
            entities[it].isSelected = false
            notifyItemChanged(it)
        }
        entities[position].isSelected = true
        selectedItemPosition = position
        notifyItemChanged(position)
    }

    override fun getItemCount() = entities.size

    override fun getItemViewType(position: Int) = entities[position].layoutId

    class RadioActionItemHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        fun populate(model: RadioViewType) {
            (itemView as Populatable<RadioViewType>).populate(model)
        }
    }

}