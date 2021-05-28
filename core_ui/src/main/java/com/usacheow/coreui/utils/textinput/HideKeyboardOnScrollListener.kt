package com.usacheow.coreui.utils.textinput

import androidx.recyclerview.widget.RecyclerView

class HideKeyboardOnScrollListener : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        recyclerView.hideKeyboard()
    }
}