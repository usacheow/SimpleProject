package com.kapmayn.coreuikit.widgets

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.kapmayn.coreuikit.R
import com.kapmayn.coreuikit.adapters.ViewTypePagerAdapter
import com.kapmayn.coreuikit.base.Populatable
import com.kapmayn.coreuikit.base.ViewType
import com.kapmayn.coreuikit.fragments.PhotoViewerObject
import kotlinx.android.synthetic.main.photo_pager_view.view.photoViewPager
import kotlinx.android.synthetic.main.photo_pager_view.view.photoViewPagerIndicator

class PhotoPagerView
@JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), Populatable<PhotoPagerViewObject> {

    override fun populate(model: PhotoPagerViewObject) {
        val adapter = ViewTypePagerAdapter(model.photoUrlList.map {
            PhotoItem(it) {
                val data = PhotoViewerObject(model.photoUrlList, photoViewPager.currentItem)
                model.onClickAction(data)
            }
        })

        photoViewPager.adapter = adapter
        photoViewPagerIndicator.setupWithViewPager(photoViewPager, model.photoUrlList.size)
    }
}

data class PhotoPagerViewObject(
    val photoUrlList: List<String>,
    val onClickAction: (PhotoViewerObject) -> Unit
) : ViewType(R.layout.photo_pager_view)