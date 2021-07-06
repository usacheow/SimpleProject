package com.usacheow.featureotp.navigation

import com.usacheow.coremediator.FeatureNavDirection
import com.usacheow.coremediator.OtpMediator
import com.usacheow.featureotp.R
import com.usacheow.featureotp.SmsCodeModalFragment
import javax.inject.Inject

class OtpMediatorImpl @Inject constructor() : OtpMediator {

    override fun getOtpFlowDirection(
        codeLength: Int,
    ) = FeatureNavDirection(R.id.sms_code_nav_graph, SmsCodeModalFragment.bundle(codeLength))
}