package com.kapmayn.featurehello.presentation.router

import android.content.Context
import com.kapmayn.core.presentation.base.Router

interface HelloFeatureRouter : Router {

    fun openWorldScreen(context: Context)
}