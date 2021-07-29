package com.usacheow.appdemo

import androidx.fragment.app.Fragment
import com.usacheow.coremediator.AuthorizationMediator
import com.usacheow.coremediator.OnBoardingMediator
import com.usacheow.coremediator.OtpMediator
import com.usacheow.coreui.base.Router
import javax.inject.Inject

class DemoRouter @Inject constructor(
    fragment: Fragment,
    private val authorizationMediator: AuthorizationMediator,
    private val onBoardingMediator: OnBoardingMediator,
    private val otpMediator: OtpMediator,
) : Router(fragment) {

    fun fromDemoToFontsScreen() {
        navigateTo(DemoFragmentDirections.actionDemoFragmentToFontsFragment())
    }

    fun fromDemoToButtonsScreen() {
        navigateTo(DemoFragmentDirections.actionDemoFragmentToButtonsFragment())
    }

    fun fromDemoToTextInputsScreen() {
        navigateTo(DemoFragmentDirections.actionDemoFragmentToTextInputsFragment())
    }

    fun fromDemoToActionTilesScreen() {
        navigateTo(DemoFragmentDirections.actionDemoFragmentToActionTilesFragment())
    }

    fun fromDemoToListTilesScreen() {
        navigateTo(DemoFragmentDirections.actionDemoFragmentToListTilesFragment())
    }

    fun fromDemoToTagListScreen() {
        navigateTo(DemoFragmentDirections.actionDemoFragmentToTagListFragment())
    }

    fun fromDemoToInformationTilesScreen() {
        navigateTo(DemoFragmentDirections.actionDemoFragmentToInformationTilesFragment())
    }

    fun fromDemoToErrorMessageScreen() {
        navigateTo(DemoFragmentDirections.actionDemoFragmentToErrorMessageFragment())
    }

    fun fromDemoToNumPadScreen() {
        navigateTo(DemoFragmentDirections.actionDemoFragmentToNumPadFragment())
    }

    fun fromDemoToExampleBottomDialogScreen() {
        navigateTo(DemoFragmentDirections.actionDemoFragmentToExampleBottomDialogFragment())
    }

    fun fromDemoToExampleModalScreen() {
        navigateTo(DemoFragmentDirections.actionDemoFragmentToExampleModalFragment())
    }

    fun toOnBoardingFlow() {
        navigateTo(onBoardingMediator.getOnBoardingFlowDirection())
    }

    fun toSignUpFlow() {
        navigateTo(authorizationMediator.getSignUpFlowDirection())
    }

    fun toSignInFlow() {
        navigateTo(authorizationMediator.getSignInFlowDirection())
    }

    fun toSignInWithPhoneFlow() {
        navigateTo(authorizationMediator.getSignInWithPhoneFlowDirection())
    }

    fun toPinCodeFlow() {
        navigateTo(authorizationMediator.getPinCodeFlowDirection())
    }

    fun toSmsCodeFlow(codeLength: Int) {
        navigateTo(otpMediator.getOtpFlowDirection(codeLength))
    }
}