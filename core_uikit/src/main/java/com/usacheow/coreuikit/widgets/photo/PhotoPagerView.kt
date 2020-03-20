package com.usacheow.coreuikit.widgets.photo

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.usacheow.coreuikit.R
import com.usacheow.coreuikit.adapters.ViewTypePagerAdapter
import com.usacheow.coreuikit.base.Populatable
import com.usacheow.coreuikit.base.ViewType
import kotlinx.android.synthetic.main.photo_pager_view.view.photoViewPager
import kotlinx.android.synthetic.main.photo_pager_view.view.photoViewPagerIndicator

class PhotoPagerView
@JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), Populatable<PhotoPagerViewObject> {

    override fun populate(model: PhotoPagerViewObject) {
        val adapter = ViewTypePagerAdapter(model.photoUrlList.map {
            PhotoItem(it) {
                model.onClickAction(model.photoUrlList, photoViewPager.currentItem)
            }
        })

        photoViewPager.adapter = adapter
        photoViewPagerIndicator.setupWithViewPager(photoViewPager, model.photoUrlList.size)
    }
}

data class PhotoPagerViewObject(
    val photoUrlList: List<String>,
    val onClickAction: (List<String>, Int) -> Unit
) : ViewType(R.layout.photo_pager_view)