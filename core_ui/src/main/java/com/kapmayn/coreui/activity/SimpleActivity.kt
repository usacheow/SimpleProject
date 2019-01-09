package com.kapmayn.coreui.activity

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import com.kapmayn.core.CoreApp
import com.kapmayn.core.di.CoreProvider

abstract class SimpleActivity : AppCompatActivity() {

    protected abstract val layoutId: Int

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject((application as CoreApp).coreProvider)
        setContentView(layoutId)
        setupViews(savedInstanceState)
    }

    abstract fun inject(coreProvider: CoreProvider)

    protected open fun setupViews(savedInstanceState: Bundle?) {}
}