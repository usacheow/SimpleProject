package com.usacheow.featureotp.navigation

import android.os.Bundle
import com.usacheow.coremediator.OtpFeatureProvider
import com.usacheow.coreui.utils.navigation.WITH
import com.usacheow.coreui.utils.navigation.addArgs
import com.usacheow.coreui.utils.navigation.screen
import javax.inject.Inject
import com.usacheow.featureotp.R as FeatureR

class OtpFeatureProviderImpl @Inject constructor() : OtpFeatureProvider {

    override fun getOtpFlowDirection(
        args: OtpFeatureProvider.OtpArgs,
    ) = screen(FeatureR.id.sms_code_nav_graph) WITH Bundle().addArgs(args)
}