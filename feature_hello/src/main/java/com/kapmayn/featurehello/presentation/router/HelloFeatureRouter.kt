package com.kapmayn.featurehello.presentation.router

import android.content.Context
import com.kapmayn.coreuikit.base.Router
import com.kapmayn.diproviders.mediator.WorldMediator
import javax.inject.Inject

class HelloFeatureRouter
@Inject constructor(
    private val worldApi: WorldMediator
) : Router {

    fun openWorldScreen(context: Context) {
        worldApi.openWorldScreen(context)
    }
}