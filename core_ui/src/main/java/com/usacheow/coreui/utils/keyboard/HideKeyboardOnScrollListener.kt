package com.usacheow.coreui.utils.keyboard

import androidx.recyclerview.widget.RecyclerView
import com.usacheow.coreui.utils.textinput.hideKeyboard

class HideKeyboardOnScrollListener : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        recyclerView.hideKeyboard()
    }
}