package com.usacheow.featurehello.presentation.viewmodels

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class HelloViewModel
@Inject constructor(
    private val resources: Resources
) : ViewModel() {

    val x = 5
}