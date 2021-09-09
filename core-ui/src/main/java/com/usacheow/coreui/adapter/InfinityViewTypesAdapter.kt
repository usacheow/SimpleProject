package com.usacheow.coreui.adapter

import com.usacheow.coreui.adapter.base.ViewState

class InfinityViewTypesAdapter(
    entities: List<ViewState> = emptyList()
) : ViewTypesAdapter(entities) {

    override fun getIndexByPosition(position: Int) = position % getData().size

    override fun getItemCount() = when {
        getData().isNotEmpty() -> Int.MAX_VALUE
        else -> 0
    }
}