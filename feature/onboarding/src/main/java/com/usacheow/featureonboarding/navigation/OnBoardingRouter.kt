package com.usacheow.featureonboarding.navigation

import androidx.fragment.app.Fragment
import com.usacheow.corenavigation.base.Router
import javax.inject.Inject

class OnBoardingRouter @Inject constructor(
    fragment: Fragment,
) : Router(fragment)