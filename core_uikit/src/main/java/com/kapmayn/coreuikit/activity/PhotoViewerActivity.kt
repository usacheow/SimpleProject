package com.kapmayn.coreuikit.activity

import android.content.Context
import com.kapmayn.core.provider.DiProvider
import com.kapmayn.coreuikit.fragments.PhotoViewerFragment
import com.kapmayn.coreuikit.fragments.PhotoViewerObject
import com.kapmayn.coreuikit.utils.ext.start

private const val DATA_EXTRA = "DATA_EXTRA"

class PhotoViewerActivity : ContainerActivity() {

    override val INIT_FRAGMENT_TAG_KEY = "PHOTO_VIEWER_PAGE_FRAGMENT"

    companion object {
        fun newInstance(context: Context, urls: List<String>, position: Int) {
            return context.start<PhotoViewerActivity> {
                putExtra(DATA_EXTRA, PhotoViewerObject(urls, position))
            }
        }
    }

    override fun inject(diProvider: DiProvider) {
    }

    override fun getInitFragment() = PhotoViewerFragment.newInstance(
        intent.getSerializableExtra(DATA_EXTRA) as PhotoViewerObject
    )
}
