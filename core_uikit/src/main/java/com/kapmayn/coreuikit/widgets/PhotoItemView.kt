package com.kapmayn.coreuikit.widgets

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.card.MaterialCardView
import com.kapmayn.coreuikit.R
import com.kapmayn.coreuikit.base.Populatable
import com.kapmayn.coreuikit.base.ViewType
import com.kapmayn.coreuikit.utils.ext.load
import kotlinx.android.synthetic.main.view_photo_item.view.imageView

class PhotoItemView
@JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr), Populatable<PhotoItem> {

    override fun populate(model: PhotoItem) {
        imageView.load(model.url)
    }
}

data class PhotoItem(
    val url: String,
    val clickAction: () -> Unit = {}
) : ViewType(R.layout.view_photo_item)