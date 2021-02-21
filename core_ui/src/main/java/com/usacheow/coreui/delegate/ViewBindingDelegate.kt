package com.usacheow.coreui.delegate

import androidx.viewbinding.ViewBinding

interface ViewBindingDelegate<VIEW_BINDING : ViewBinding> {

    val binding: VIEW_BINDING

    fun saveBinding(binding: VIEW_BINDING)

    fun clearBinding()
}

class FragmentViewBindingDelegate<VIEW_BINDING : ViewBinding> : ViewBindingDelegate<VIEW_BINDING> {

    private var _binding: VIEW_BINDING? = null
    override val binding get() = _binding!!

    override fun saveBinding(binding: VIEW_BINDING) {
        _binding = binding
    }

    override fun clearBinding() {
        _binding = null
    }
}

class ActivityViewBindingDelegate<VIEW_BINDING : ViewBinding> : ViewBindingDelegate<VIEW_BINDING> {

    private var _binding: VIEW_BINDING? = null
    override val binding get() = _binding!!

    override fun saveBinding(binding: VIEW_BINDING) {
        _binding = binding
    }

    override fun clearBinding() { }
}