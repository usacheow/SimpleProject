package com.usacheow.coreui.delegate

import android.view.View
import androidx.viewbinding.ViewBinding

class ViewBindingDelegate<VIEW_BINDING : ViewBinding> {

    private var _binding: VIEW_BINDING? = null
    val binding get() = _binding!!
    val rootView get() = binding.root

    fun save(binding: VIEW_BINDING) {
        _binding = binding
    }

    fun clear() {
        _binding = null
    }
}