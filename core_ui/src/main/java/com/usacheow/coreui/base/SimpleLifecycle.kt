package com.usacheow.coreui.base

import android.os.Bundle

interface SimpleLifecycle {

    fun processArguments(bundle: Bundle?) = Unit

    fun setupViews(savedInstanceState: Bundle?) = Unit

    fun subscribe() = Unit

    fun clearViews() = Unit
}