package com.kapmayn.featureworld.featureapi

import android.content.Context
import com.kapmayn.corefeature.WorldFeatureApi
import com.kapmayn.featureworld.activity.WorldActivity
import javax.inject.Inject

class WorldFeatureApiImpl
@Inject constructor() : WorldFeatureApi {

    override fun openWorldScreen(context: Context) {
        WorldActivity.start(context)
    }
}