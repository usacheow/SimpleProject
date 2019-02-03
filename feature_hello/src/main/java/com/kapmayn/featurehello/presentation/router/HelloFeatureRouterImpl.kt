package com.kapmayn.featurehello.presentation.router

import android.content.Context
import com.kapmayn.diproviders.featureapi.WorldFeatureApi
import javax.inject.Inject

class HelloFeatureRouterImpl
@Inject constructor(
    private val worldApi: WorldFeatureApi
) : HelloFeatureRouter {

    override fun openWorldScreen(context: Context) {
        worldApi.openWorldScreen(context)
    }
}