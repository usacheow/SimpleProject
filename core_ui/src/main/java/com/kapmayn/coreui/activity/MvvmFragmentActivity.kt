package com.kapmayn.coreui.activity

import androidx.fragment.app.Fragment
import com.kapmayn.core.base.Router

abstract class MvvmFragmentActivity<ROUTER : Router> : MvvmActivity<ROUTER>() {

    abstract val transactionContainerId: Int

    open fun show(fragment: Fragment) {}
}