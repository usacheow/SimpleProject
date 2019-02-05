package com.kapmayn.featureworld.presentation.viewmodels

import android.content.res.Resources
import com.kapmayn.core.presentation.viewmodels.SimpleRxViewModel
import javax.inject.Inject

class WorldViewModel
@Inject constructor(
    private val resources: Resources
) : SimpleRxViewModel() {

    val x = 5
}