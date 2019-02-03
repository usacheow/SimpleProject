package com.kapmayn.featureworld.di

import android.content.Context
import com.kapmayn.diproviders.featureapi.WorldFeatureApi
import com.kapmayn.featureworld.presentation.activity.WorldActivity
import javax.inject.Inject

class WorldFeatureApiImpl
@Inject constructor() : WorldFeatureApi {

    override fun openWorldScreen(context: Context) {
        WorldActivity.start(context)
    }
}