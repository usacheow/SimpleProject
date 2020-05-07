package com.usacheow.coreuikit.activity

import android.content.Context
import com.usacheow.coreuikit.fragments.PhotoViewerFragment
import com.usacheow.coreuikit.fragments.PhotoViewerObject
import com.usacheow.coreuikit.utils.ext.start
import com.usacheow.diprovider.DiProvider

private const val DATA_EXTRA = "DATA_EXTRA"

class PhotoViewerActivity : ContainerActivity() {

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
