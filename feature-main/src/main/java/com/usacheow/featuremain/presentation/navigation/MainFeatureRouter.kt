package com.usacheow.featuremain.presentation.navigation

import androidx.fragment.app.Fragment
import com.usacheow.coreui.base.Router
import com.usacheow.coreui.utils.navigation.OPEN_IN
import com.usacheow.coreui.utils.navigation.WITH
import com.usacheow.coreui.utils.navigation.screen
import com.usacheow.featuremain.R
import com.usacheow.featuremain.presentation.fragment.BFragment
import javax.inject.Inject

class MainFeatureRouter @Inject constructor(
    fragment: Fragment,
) : Router(fragment) {

    fun fromAtoBScreen(itemNumber: Int) {
        screen(R.id.action_aFragment_to_bFragment) WITH BFragment.bundle(itemNumber) OPEN_IN navController
    }
}