package com.kapmayn.coreuikit.utils

import androidx.recyclerview.widget.RecyclerView
import com.kapmayn.coreuikit.utils.ext.hideKeyboard

class ListWithKeyboardScrollListener : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        recyclerView.hideKeyboard()
    }
}