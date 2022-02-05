package com.usacheow.featureotp.navigation

import android.os.Bundle
import com.usacheow.coremediator.OtpFeatureProvider
import com.usacheow.coreui.navigation.WITH
import com.usacheow.coreui.navigation.addArgs
import com.usacheow.coreui.navigation.screen
import javax.inject.Inject
import com.usacheow.featureotp.R as FeatureR

class OtpFeatureProviderImpl @Inject constructor() : OtpFeatureProvider {

    override fun getOtpFlowDirection(
        args: OtpFeatureProvider.OtpArgs,
    ) = screen(FeatureR.id.sms_code_nav_graph) WITH Bundle().addArgs(args)
}