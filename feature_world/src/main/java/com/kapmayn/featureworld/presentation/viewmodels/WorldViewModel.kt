package com.kapmayn.featureworld.presentation.viewmodels

import android.content.res.Resources
import com.kapmayn.core.presentation.viewmodels.RxViewModel
import javax.inject.Inject

class WorldViewModel
@Inject constructor(
    private val resources: Resources
) : RxViewModel() {

    val x = 5
}