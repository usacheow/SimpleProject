package com.usacheow.coreui.adapter

import com.usacheow.coreui.adapter.base.TagViewType

class SingleSelectionViewTypesAdapter(
    entities: List<TagViewType> = emptyList()
) : BaseSelectionModeViewTypesAdapter(entities) {

    private var selectedItemPosition: Int? = null

    init {
        prepareItems()
    }

    private fun prepareItems() {
        entities.forEachIndexed { index, actionItem ->
            if (actionItem.isSelected && selectedItemPosition == null) {
                selectedItemPosition = index
            } else {
                actionItem.isSelected = false
            }
            actionItem.onSelectAction = { onSelected(index) }
        }
    }

    private fun onSelected(position: Int) {
        if (position == selectedItemPosition) {
            return
        }
        selectedItemPosition?.let {
            entities[it].isSelected = false
            notifyItemChanged(it)
        }
        entities[position].isSelected = true
        selectedItemPosition = position
        notifyItemChanged(position)
    }

    fun update(newItems: List<TagViewType>) {
        entities = newItems
        selectedItemPosition = null
        prepareItems()
        notifyDataSetChanged()
    }
}