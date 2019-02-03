package com.kapmayn.core.presentation.activity

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import com.kapmayn.diproviders.DiApp
import com.kapmayn.diproviders.DiProvider

abstract class SimpleActivity : AppCompatActivity() {

    protected abstract val layoutId: Int

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject((application as DiApp).diProvider)
        setContentView(layoutId)
        setupViews(savedInstanceState)
    }

    abstract fun inject(diProvider: DiProvider)

    protected open fun setupViews(savedInstanceState: Bundle?) {}
}