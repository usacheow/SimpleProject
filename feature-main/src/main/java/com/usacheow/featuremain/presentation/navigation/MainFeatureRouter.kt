package com.usacheow.featuremain.presentation.navigation

import androidx.fragment.app.Fragment
import com.usacheow.coreui.base.Router
import com.usacheow.featuremain.presentation.fragment.AFragmentDirections
import javax.inject.Inject

class MainFeatureRouter @Inject constructor(
    fragment: Fragment,
) : Router(fragment) {

    fun fromAtoBScreen(itemNumber: Int) {
        navigateTo(AFragmentDirections.actionAFragmentToBFragment(itemNumber = itemNumber))
    }
}