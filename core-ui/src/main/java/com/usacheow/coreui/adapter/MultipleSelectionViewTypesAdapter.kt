package com.usacheow.coreui.adapter

import com.usacheow.coreui.adapter.base.TagViewType

class MultipleSelectionViewTypesAdapter(
    entities: List<TagViewType> = emptyList(),
) : BaseSelectionModeViewTypesAdapter(entities) {

    init {
        prepareItems()
    }

    private fun prepareItems() {
        entities.forEachIndexed { index, actionItem ->
            actionItem.onSelectAction = { onSelected(index) }
        }
    }

    private fun onSelected(position: Int) {
        entities[position].apply {
            isSelected = !isSelected
        }
        notifyItemChanged(position)
    }

    fun update(newItems: List<TagViewType>) {
        entities = newItems
        prepareItems()
        notifyDataSetChanged()
    }
}