package com.usacheow.featurehello.presentation.router

import android.content.Context
import androidx.fragment.app.Fragment
import com.usacheow.coreui.base.Router
import javax.inject.Inject

class HelloFeatureRouter
@Inject constructor(fragment: Fragment) : Router(fragment) {

    fun openWorldScreen(context: Context) {
    }
}