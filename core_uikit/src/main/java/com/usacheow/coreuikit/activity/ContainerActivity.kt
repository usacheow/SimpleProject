package com.usacheow.coreuikit.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.usacheow.coreuikit.R
import com.usacheow.coreuikit.base.IBackListener
import com.usacheow.coreuikit.base.IContainer
import com.usacheow.coreuikit.utils.ext.hashTag
import com.usacheow.coreuikit.utils.ext.inTransaction
import com.usacheow.coreuikit.utils.ext.replaceFragmentIn

abstract class ContainerActivity : SimpleActivity(), IContainer {

    override val layoutId = R.layout.frg_container

    private var initFragmentHashTag: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (initFragmentHashTag == null || supportFragmentManager.findFragmentByTag(initFragmentHashTag) == null) {
            val fragment = getInitFragment()
            initFragmentHashTag = fragment.hashTag()
            supportFragmentManager.replaceFragmentIn(R.id.fragmentContainer, getInitFragment(), true, initFragmentHashTag!!)
        }
    }

    override fun show(fragment: Fragment, needAddToBackStack: Boolean) {
//        val activeFragment = childFragmentManager.findFragmentById(R.id.fragmentContainer)
//        val transaction = childFragmentManager.createReplaceTransactionIn(R.id.fragmentContainer, fragment, needAddToBackStack)
//        if(activeFragment is SimpleFragment) {
//            fragment.sharedElementEnterTransition = TransitionInflater.from(context)
//                .inflateTransition(R.transition.transition_default_screen)
//            transaction.addSharedElements(*activeFragment.getSharedViews().toTypedArray())
//        }
//        transaction.commit()

        supportFragmentManager.inTransaction {
            setCustomAnimations(
                R.anim.anim_enter_from_right,
                R.anim.anim_exit_to_left,
                R.anim.anim_enter_from_left,
                R.anim.anim_exit_to_right
            )
            replace(R.id.fragmentContainer, fragment)
            if (needAddToBackStack) addToBackStack(null)
            this
        }
    }

    protected abstract fun getInitFragment(): Fragment

    override fun reset() {
        while (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStackImmediate()
        }
    }

    override fun onBackPressed() {
        val activeFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
        val backStackEntryCount = supportFragmentManager.backStackEntryCount

        if (activeFragment is IBackListener && activeFragment.onBackPressed()) {
        } else if (activeFragment != null && backStackEntryCount > 1) {
            supportFragmentManager.popBackStackImmediate()
        } else {
            finish()
        }
    }
}