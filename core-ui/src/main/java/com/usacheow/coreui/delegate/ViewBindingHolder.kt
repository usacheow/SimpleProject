package com.usacheow.coreui.delegate

import androidx.viewbinding.ViewBinding

interface ViewBindingHolder<VIEW_BINDING : ViewBinding> {

    val binding: VIEW_BINDING

    fun saveBinding(binding: VIEW_BINDING)

    fun clearBinding()
}

class FragmentViewBindingHolder<VIEW_BINDING : ViewBinding> : ViewBindingHolder<VIEW_BINDING> {

    private var _binding: VIEW_BINDING? = null
    override val binding get() = _binding!!

    override fun saveBinding(binding: VIEW_BINDING) {
        _binding = binding
    }

    override fun clearBinding() {
        _binding = null
    }
}

class ActivityViewBindingHolder<VIEW_BINDING : ViewBinding> : ViewBindingHolder<VIEW_BINDING> {

    private var _binding: VIEW_BINDING? = null
    override val binding get() = _binding!!

    override fun saveBinding(binding: VIEW_BINDING) {
        _binding = binding
    }

    override fun clearBinding() { }
}