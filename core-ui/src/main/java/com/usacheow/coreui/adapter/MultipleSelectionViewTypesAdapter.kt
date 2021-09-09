package com.usacheow.coreui.adapter

import com.usacheow.coreui.adapter.base.TagViewState

class MultipleSelectionViewTypesAdapter(
    entities: List<TagViewState> = emptyList(),
) : BaseSelectionModeViewTypesAdapter(entities) {

    init {
        prepareItems()
    }

    private fun prepareItems() {
        entities.forEachIndexed { index, actionItem ->
            actionItem.clickListener = { onClicked(index) }
        }
    }

    private fun onClicked(position: Int) {
        entities[position].apply {
            isSelected = !isSelected
        }
        notifyItemChanged(position)
    }

    fun update(newItems: List<TagViewState>) {
        entities = newItems
        prepareItems()
        notifyDataSetChanged()
    }
}