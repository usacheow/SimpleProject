package com.kapmayn.featurehello.router

import android.content.Context
import com.kapmayn.core.base.Router

interface HelloFeatureRouter : Router {

    fun openWorldScreen(context: Context)
}