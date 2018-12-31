package com.kapmayn.coreui.activity

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import com.kapmayn.di.BaseProvider
import com.kapmayn.di.DaggerApp

abstract class SimpleActivity : AppCompatActivity() {

    protected abstract val layoutId: Int

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject((application as DaggerApp).baseProvider)
        setContentView(layoutId)
        setupViews(savedInstanceState)
    }

    abstract fun inject(coreProvider: BaseProvider)

    protected open fun setupViews(savedInstanceState: Bundle?) {}
}