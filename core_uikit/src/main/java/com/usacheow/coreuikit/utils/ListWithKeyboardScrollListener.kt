package com.usacheow.coreuikit.utils

import androidx.recyclerview.widget.RecyclerView
import com.usacheow.coreuikit.utils.ext.hideKeyboard

class ListWithKeyboardScrollListener : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        recyclerView.hideKeyboard()
    }
}