package com.kapmayn.coreuikit.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.kapmayn.coreuikit.R
import com.kapmayn.coreuikit.base.IContainer
import com.kapmayn.coreuikit.fragments.SimpleFragment
import com.kapmayn.coreuikit.utils.ext.inTransaction
import com.kapmayn.coreuikit.utils.ext.replaceFragmentIn

abstract class ContainerActivity : SimpleActivity(), IContainer {

    override val layoutId = R.layout.frg_container

    abstract val INIT_FRAGMENT_TAG_KEY: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (supportFragmentManager.findFragmentByTag(INIT_FRAGMENT_TAG_KEY) == null) {
            supportFragmentManager.replaceFragmentIn(R.id.fragmentContainer, getInitFragment(), true, INIT_FRAGMENT_TAG_KEY)
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

        if (activeFragment is SimpleFragment && activeFragment.onBackPressed()) {
        } else if (activeFragment != null && backStackEntryCount > 1) {
            supportFragmentManager.popBackStackImmediate()
        } else {
            finish()
        }
    }

    override fun onBackClicked() = false
}