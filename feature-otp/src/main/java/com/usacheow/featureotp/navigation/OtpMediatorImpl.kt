package com.usacheow.featureotp.navigation

import android.os.Bundle
import com.usacheow.coremediator.OtpMediator
import com.usacheow.coreui.utils.navigation.WITH
import com.usacheow.coreui.utils.navigation.addArgs
import com.usacheow.coreui.utils.navigation.screen
import com.usacheow.featureotp.R
import javax.inject.Inject

class OtpMediatorImpl @Inject constructor() : OtpMediator {

    override fun getOtpFlowDirection(
        args: OtpMediator.OtpArgs,
    ) = screen(R.id.sms_code_nav_graph) WITH Bundle().addArgs(args)
}