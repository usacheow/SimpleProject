package com.usacheow.corenavigation

import android.os.Parcelable
import androidx.annotation.DrawableRes
import com.usacheow.corecommon.container.TextSource
import com.usacheow.corecommon.navigation.FeatureNavDirection
import kotlinx.parcelize.Parcelize

interface OnBoardingFeatureProvider {

    fun getOnBoardingFlowDirection(
        args: OnBoardingArgs,
        nextScreenDirection: FeatureNavDirection,
    ): FeatureNavDirection

    @Parcelize
    data class OnBoardingArgs(
        val pages: List<Page>,
    ) : Parcelable {

        @Parcelize
        data class Page(
            @DrawableRes val defaultImageRes: Int? = null,
            val imageUrl: String? = null,
            val title: TextSource.Simple,
            val description: TextSource.Simple,
        ) : Parcelable
    }
}