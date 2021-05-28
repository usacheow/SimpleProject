package com.usacheow.featuremain.presentation.navigation

import androidx.fragment.app.Fragment
import com.usacheow.coreui.base.Router
import javax.inject.Inject

class MainFeatureRouter @Inject constructor(
    fragment: Fragment,
) : Router(fragment)