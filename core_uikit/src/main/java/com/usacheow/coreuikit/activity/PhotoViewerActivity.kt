package com.usacheow.coreuikit.activity

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.transition.TransitionSet
import com.usacheow.coreuikit.R
import com.usacheow.coreuikit.base.IContainer
import com.usacheow.coreuikit.delegate.ContainerDelegate
import com.usacheow.coreuikit.fragments.PhotoViewerFragment
import com.usacheow.coreuikit.fragments.PhotoViewerObject
import com.usacheow.coreuikit.utils.ext.start
import com.usacheow.diprovider.DiProvider

private const val DATA_EXTRA = "DATA_EXTRA"

class PhotoViewerActivity : SimpleActivity(), IContainer {

    override val layoutId = R.layout.frg_container

    private val containerDelegate by lazy { ContainerDelegate() }

    companion object {
        fun newInstance(context: Context, urls: List<String>, position: Int) {
            return context.start<PhotoViewerActivity> {
                putExtra(DATA_EXTRA, PhotoViewerObject(urls, position))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        containerDelegate.onCreate(supportFragmentManager, ::getInitFragment)
    }

    override fun show(fragment: Fragment, needAddToBackStack: Boolean, transition: TransitionSet) {
        containerDelegate.show(supportFragmentManager, fragment, needAddToBackStack, transition)
    }

    override fun reset() {
        containerDelegate.reset(supportFragmentManager)
    }

    override fun onBackPressed() {
        if (!containerDelegate.onBackPressed(supportFragmentManager)) {
            finish()
        }
    }

    override fun inject(diProvider: DiProvider) = Unit

    private fun getInitFragment() = PhotoViewerFragment.newInstance(
        intent.getSerializableExtra(DATA_EXTRA) as PhotoViewerObject
    )
}
