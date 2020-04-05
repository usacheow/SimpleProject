package com.usacheow.featurehello.presentation.viewmodels

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class AViewModel
@Inject constructor(
    private val resources: Resources
) : ViewModel() {

    var x = 0
}