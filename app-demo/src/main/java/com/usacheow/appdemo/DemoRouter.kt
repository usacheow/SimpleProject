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

    fun openFontsScreen() {
        navigateTo(DemoFragmentDirections.actionDemoFragmentToFontsFragment())
    }

    fun openButtonsScreen() {
        navigateTo(DemoFragmentDirections.actionDemoFragmentToButtonsFragment())
    }

    fun openTextInputsScreen() {
        navigateTo(DemoFragmentDirections.actionDemoFragmentToTextInputsFragment())
    }

    fun openActionTilesScreen() {
        navigateTo(DemoFragmentDirections.actionDemoFragmentToActionTilesFragment())
    }

    fun openListTilesScreen() {
        navigateTo(DemoFragmentDirections.actionDemoFragmentToListTilesFragment())
    }

    fun openTagListScreen() {
        navigateTo(DemoFragmentDirections.actionDemoFragmentToTagListFragment())
    }

    fun openInformationTilesScreen() {
        navigateTo(DemoFragmentDirections.actionDemoFragmentToInformationTilesFragment())
    }

    fun openErrorMessageScreen() {
        navigateTo(DemoFragmentDirections.actionDemoFragmentToErrorMessageFragment())
    }

    fun openNumPadScreen() {
        navigateTo(DemoFragmentDirections.actionDemoFragmentToNumPadFragment())
    }

    fun openExampleBottomDialogScreen() {
        navigateTo(DemoFragmentDirections.actionDemoFragmentToExampleBottomDialogFragment())
    }

    fun openExampleModalScreen() {
        navigateTo(DemoFragmentDirections.actionDemoFragmentToExampleModalFragment())
    }

    fun openOnBoardingScreen() {
        navigateTo(onBoardingMediator.getOnBoardingFlowDirection())
    }

    fun openSignUpScreen() {
        navigateTo(authorizationMediator.getSignUpFlowDirection())
    }

    fun openSignInScreen() {
        navigateTo(authorizationMediator.getSignInFlowDirection())
    }

    fun openSignInWithPhoneScreen() {
        navigateTo(authorizationMediator.getSignInWithPhoneFlowDirection())
    }

    fun openPinCodeScreen() {
        navigateTo(authorizationMediator.getPinCodeFlowDirection())
    }

    fun openSmsCodeScreen(codeLength: Int) {
        navigateTo(otpMediator.getOtpFlowDirection(codeLength))
    }
}