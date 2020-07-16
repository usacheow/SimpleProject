package com.usacheow.coreui.utils

import androidx.recyclerview.widget.RecyclerView
import com.usacheow.coreui.utils.ext.hideKeyboard

class ListWithKeyboardScrollListener : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        recyclerView.hideKeyboard()
    }
}