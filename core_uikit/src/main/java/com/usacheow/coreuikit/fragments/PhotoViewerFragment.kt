package com.usacheow.coreuikit.fragments

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.usacheow.coreuikit.R
import com.usacheow.coreuikit.adapters.ViewTypePagerAdapter
import com.usacheow.coreuikit.utils.ext.openShareDialog
import com.usacheow.coreuikit.widgets.photo.PhotoItem
import com.usacheow.diprovider.DiProvider
import kotlinx.android.synthetic.main.fragment_photo_viewer.photoViewPager
import kotlinx.android.synthetic.main.fragment_photo_viewer.photoViewPagerIndicator
import kotlinx.android.synthetic.main.fragment_photo_viewer.toolbar
import java.io.Serializable

private const val DATA_EXTRA = "DATA_EXTRA"

class PhotoViewerFragment : SimpleFragment() {

    private var urls = emptyList<String>()

    override val layoutId = R.layout.fragment_photo_viewer

    companion object {
        fun newInstance(data: PhotoViewerObject): Fragment {
            return PhotoViewerFragment().apply {
                arguments = bundleOf(DATA_EXTRA to data)
            }
        }
    }

    override fun inject(appProvider: DiProvider) {
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        initToolbar()

        val data = arguments?.getSerializable(DATA_EXTRA) as PhotoViewerObject?
        urls = data?.urls ?: emptyList()
        val position = data?.position ?: 0

        photoViewPager.adapter = ViewTypePagerAdapter(urls.map { PhotoItem(it) })
        photoViewPagerIndicator.setupWithViewPager(photoViewPager, urls.size)
        photoViewPager.currentItem = position
    }

    private fun initToolbar() {
        toolbar.setNavigationOnClickListener { activity?.onBackPressed() }
        toolbar.inflateMenu(R.menu.menu_share)
        toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_share -> sharePhotoUrl()
                else -> return@setOnMenuItemClickListener false
            }
            return@setOnMenuItemClickListener true
        }
    }

    private fun sharePhotoUrl() {
        val url = urls[photoViewPager.currentItem]
        openShareDialog(url)
    }
}

data class PhotoViewerObject(
    val urls: List<String>,
    val position: Int
) : Serializable