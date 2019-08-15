package com.kapmayn.featureworld

import android.content.Context
import com.kapmayn.diproviders.mediator.WorldMediator
import com.kapmayn.featureworld.presentation.activity.WorldActivity
import javax.inject.Inject

class WorldMediatorImpl
@Inject constructor() : WorldMediator {

    override fun openWorldScreen(context: Context) {
        WorldActivity.start(context)
    }
}