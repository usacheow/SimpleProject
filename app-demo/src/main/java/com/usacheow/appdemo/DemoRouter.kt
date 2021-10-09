package com.usacheow.appdemo

import androidx.fragment.app.Fragment
import com.usacheow.coremediator.AuthorizationMediator
import com.usacheow.coremediator.OnBoardingMediator
import com.usacheow.coremediator.OtpMediator
import com.usacheow.coreui.base.Router
import com.usacheow.coreui.utils.navigation.FeatureNavDirection
import com.usacheow.coreui.utils.navigation.ResetTo
import com.usacheow.coreui.utils.navigation.openIn
import javax.inject.Inject

class DemoRouter @Inject constructor(
    fragment: Fragment,
    private val authorizationMediator: AuthorizationMediator,
    private val onBoardingMediator: OnBoardingMediator,
    private val otpMediator: OtpMediator,
) : Router(fragment) {

    fun fromDemoToFontsScreen() {
        DemoFragmentDirections.actionDemoFragmentToFontsFragment().openIn(navController)
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

    fun fromDemoToErrorMessageScreen() {
        DemoFragmentDirections.actionDemoFragmentToErrorMessageFragment().openIn(navController)
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

    fun toOnBoardingFlow() {
        val nextDirection = FeatureNavDirection(R.id.main_nav_graph, resetTo = ResetTo(R.id.demo_app_nav_graph))
        onBoardingMediator.getOnBoardingFlowDirection(nextDirection).openIn(navController)
    }

    fun toSignUpFlow() {
        val nextDirection = FeatureNavDirection(R.id.main_nav_graph, resetTo = ResetTo(R.id.demo_app_nav_graph))
        authorizationMediator.getSignUpFlowDirection(nextDirection).openIn(navController)
    }

    fun toSignInFlow() {
        val nextDirection = FeatureNavDirection(R.id.main_nav_graph, resetTo = ResetTo(R.id.demo_app_nav_graph))
        authorizationMediator.getSignInFlowDirection(nextDirection).openIn(navController)
    }

    fun toSignInWithPhoneFlow() {
        val nextDirection = FeatureNavDirection(R.id.main_nav_graph, resetTo = ResetTo(R.id.demo_app_nav_graph))
        authorizationMediator.getSignInWithPhoneFlowDirection(nextDirection).openIn(navController)
    }

    fun toPinCodeFlow() {
        val nextDirection = FeatureNavDirection(R.id.main_nav_graph, resetTo = ResetTo(R.id.demo_app_nav_graph))
        authorizationMediator.getPinCodeFlowDirection(nextDirection).openIn(navController)
    }

    fun toSmsCodeFlow(codeLength: Int) {
        otpMediator.getOtpFlowDirection(codeLength).openIn(navController)
    }
}