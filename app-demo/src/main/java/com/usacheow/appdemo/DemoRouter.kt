package com.usacheow.appdemo

import androidx.fragment.app.Fragment
import com.usacheow.core.navigation.FeatureNavDirection
import com.usacheow.core.navigation.ResetTo
import com.usacheow.corenavigation.AuthorizationFeatureProvider
import com.usacheow.corenavigation.OnBoardingFeatureProvider
import com.usacheow.corenavigation.base.OPEN_IN
import com.usacheow.corenavigation.base.Router
import com.usacheow.corenavigation.base.openIn
import javax.inject.Inject
import com.usacheow.appdemo.R as DemoAppR

class DemoRouter @Inject constructor(
    fragment: Fragment,
    private val authorizationFeatureProvider: AuthorizationFeatureProvider,
    private val onBoardingFeatureProvider: OnBoardingFeatureProvider,
) : Router(fragment) {

    fun fromDemoToTypographyScreen() {
        DemoFragmentDirections.actionDemoFragmentToTypographyFragment().openIn(navController)
    }

    fun fromDemoToPaletteScreen() {
        DemoFragmentDirections.actionDemoFragmentToPaletteFragment().openIn(navController)
    }

    fun fromDemoToButtonsScreen() {
        DemoFragmentDirections.actionDemoFragmentToButtonsFragment().openIn(navController)
    }

    fun fromDemoToTextInputsScreen() {
        DemoFragmentDirections.actionDemoFragmentToTextInputsFragment().openIn(navController)
    }

    fun fromDemoToActionTilesScreen() {
        DemoFragmentDirections.actionDemoFragmentToActionTilesFragment().openIn(navController)
    }

    fun fromDemoToListTilesScreen() {
        DemoFragmentDirections.actionDemoFragmentToListTilesFragment().openIn(navController)
    }

    fun fromDemoToTagListScreen() {
        DemoFragmentDirections.actionDemoFragmentToTagListFragment().openIn(navController)
    }

    fun fromDemoToInformationTilesScreen() {
        DemoFragmentDirections.actionDemoFragmentToInformationTilesFragment().openIn(navController)
    }

    fun fromDemoToMessageScreen() {
        DemoFragmentDirections.actionDemoFragmentToMessageFragment().openIn(navController)
    }

    fun fromDemoToNumPadScreen() {
        DemoFragmentDirections.actionDemoFragmentToNumPadFragment().openIn(navController)
    }

    fun fromDemoToExampleBottomDialogScreen() {
        DemoFragmentDirections.actionDemoFragmentToExampleBottomDialogFragment().openIn(navController)
    }

    fun fromDemoToExampleModalScreen() {
        DemoFragmentDirections.actionDemoFragmentToExampleModalFragment().openIn(navController)
    }

    fun toOnBoardingFlow(args: OnBoardingFeatureProvider.OnBoardingArgs) {
        val nextDirection = FeatureNavDirection(
            DemoAppR.id.main_nav_graph,
            resetTo = ResetTo(DemoAppR.id.demo_app_nav_graph),
        )
        onBoardingFeatureProvider.getOnBoardingFlowDirection(args, nextDirection) OPEN_IN navController
    }

    fun toSignUpFlow() {
        val nextDirection = FeatureNavDirection(
            DemoAppR.id.main_nav_graph,
            resetTo = ResetTo(DemoAppR.id.demo_app_nav_graph),
        )
        authorizationFeatureProvider.getSignUpFlowDirection(nextDirection) OPEN_IN navController
    }

    fun toSignInFlow() {
        val nextDirection = FeatureNavDirection(
            DemoAppR.id.main_nav_graph,
            resetTo = ResetTo(DemoAppR.id.demo_app_nav_graph),
        )
        authorizationFeatureProvider.getSignInFlowDirection(nextDirection) OPEN_IN navController
    }

    fun toSignInWithPhoneFlow() {
        val nextDirection = FeatureNavDirection(
            DemoAppR.id.main_nav_graph,
            resetTo = ResetTo(DemoAppR.id.demo_app_nav_graph),
        )
        authorizationFeatureProvider.getSignInWithPhoneFlowDirection(nextDirection) OPEN_IN navController
    }

    fun toPinCodeFlow() {
        val nextDirection = FeatureNavDirection(
            DemoAppR.id.main_nav_graph,
            resetTo = ResetTo(DemoAppR.id.demo_app_nav_graph),
        )
        authorizationFeatureProvider.getPinCodeFlowDirection(nextDirection) OPEN_IN navController
    }
}