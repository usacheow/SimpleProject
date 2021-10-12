package com.usacheow.coreui.screen.base

import android.os.Bundle

interface SimpleLifecycle {

    fun setupViews(savedInstanceState: Bundle?) = Unit

    fun subscribe() = Unit

    fun clearViews() = Unit
}