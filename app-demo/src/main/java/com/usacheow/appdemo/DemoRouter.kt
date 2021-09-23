package com.usacheow.appdemo

import androidx.fragment.app.Fragment
import com.usacheow.coremediator.AuthorizationMediator
import com.usacheow.coremediator.OnBoardingMediator
import com.usacheow.coremediator.OtpMediator
import com.usacheow.coreui.base.Router
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
        onBoardingMediator.getOnBoardingFlowDirection().openIn(navController)
    }

    fun toSignUpFlow() {
        authorizationMediator.getSignUpFlowDirection().openIn(navController)
    }

    fun toSignInFlow() {
        authorizationMediator.getSignInFlowDirection().openIn(navController)
    }

    fun toSignInWithPhoneFlow() {
        authorizationMediator.getSignInWithPhoneFlowDirection().openIn(navController)
    }

    fun toPinCodeFlow() {
        authorizationMediator.getPinCodeFlowDirection().openIn(navController)
    }

    fun toSmsCodeFlow(codeLength: Int) {
        otpMediator.getOtpFlowDirection(codeLength).openIn(navController)
    }
}