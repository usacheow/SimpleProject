package com.kapmayn.core.presentation.activity

import androidx.fragment.app.Fragment
import com.kapmayn.core.presentation.base.Router

abstract class MvvmFragmentActivity<ROUTER : Router> : MvvmActivity<ROUTER>() {

    abstract val transactionContainerId: Int

    open fun show(fragment: Fragment) {}
}