package com.kapmayn.featurehello.presentation.router

import android.content.Context
import com.kapmayn.diproviders.mediator.WorldMediator
import javax.inject.Inject

class HelloFeatureRouterImpl
@Inject constructor(
    private val worldApi: WorldMediator
) : HelloFeatureRouter {

    override fun openWorldScreen(context: Context) {
        worldApi.openWorldScreen(context)
    }
}