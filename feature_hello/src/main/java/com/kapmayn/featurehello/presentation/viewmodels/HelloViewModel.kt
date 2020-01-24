package com.kapmayn.featurehello.presentation.viewmodels

import android.content.res.Resources
import com.kapmayn.coreuikit.viewmodels.SimpleRxViewModel
import javax.inject.Inject

class HelloViewModel
@Inject constructor(
    private val resources: Resources
) : SimpleRxViewModel() {

    val x = 5
}