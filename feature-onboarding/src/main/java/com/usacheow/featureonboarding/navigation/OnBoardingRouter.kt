package com.usacheow.featureonboarding.navigation

import androidx.fragment.app.Fragment
import com.usacheow.coreui.navigation.Router
import javax.inject.Inject

class OnBoardingRouter @Inject constructor(
    fragment: Fragment,
) : Router(fragment)