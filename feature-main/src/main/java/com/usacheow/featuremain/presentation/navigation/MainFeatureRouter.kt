package com.usacheow.featuremain.presentation.navigation

import androidx.fragment.app.Fragment
import com.usacheow.coreui.navigation.Router
import com.usacheow.coreui.navigation.OPEN_IN
import com.usacheow.coreui.navigation.WITH
import com.usacheow.coreui.navigation.screen
import com.usacheow.featuremain.presentation.fragment.BFragment
import javax.inject.Inject
import com.usacheow.featuremain.R as FeatureR

class MainFeatureRouter @Inject constructor(
    fragment: Fragment,
) : Router(fragment) {

    fun fromAtoBScreen(itemNumber: Int) {
        screen(FeatureR.id.action_aFragment_to_bFragment) WITH BFragment.bundle(itemNumber) OPEN_IN navController
    }
}