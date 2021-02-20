package com.usacheow.coreui.adapters

import com.usacheow.coreui.adapters.base.TagViewType

class MultipleSelectionViewTypesAdapter(
    private var entities: List<TagViewType> = emptyList()
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