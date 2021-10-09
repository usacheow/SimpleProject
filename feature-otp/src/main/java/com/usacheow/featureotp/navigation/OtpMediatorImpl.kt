package com.usacheow.featureotp.navigation

import com.usacheow.coremediator.OtpMediator
import com.usacheow.coreui.utils.navigation.screen
import com.usacheow.coreui.utils.navigation.WITH
import com.usacheow.featureotp.R
import com.usacheow.featureotp.SmsCodeModalFragment
import javax.inject.Inject

class OtpMediatorImpl @Inject constructor() : OtpMediator {

    override fun getOtpFlowDirection(
        codeLength: Int,
    ) = screen(R.id.sms_code_nav_graph) WITH SmsCodeModalFragment.bundle(codeLength)
}