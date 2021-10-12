package com.usacheow.coreui.adapter

import com.usacheow.coreui.adapter.base.TagViewState

class SingleSelectionViewStatesAdapter(
    entities: List<TagViewState> = emptyList()
) : BaseSelectionModeViewStatesAdapter(entities) {

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
            actionItem.clickListener = { onClicked(index) }
        }
    }

    private fun onClicked(position: Int) {
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

    fun update(newItems: List<TagViewState>) {
        entities = newItems
        selectedItemPosition = null
        prepareItems()
        notifyDataSetChanged()
    }
}