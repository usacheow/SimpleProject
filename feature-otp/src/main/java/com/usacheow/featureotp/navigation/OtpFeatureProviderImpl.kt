package com.usacheow.featureotp.navigation

import android.os.Bundle
import com.usacheow.corenavigation.OtpFeatureProvider
import com.usacheow.corenavigation.base.WITH
import com.usacheow.corenavigation.base.addArgs
import com.usacheow.corenavigation.base.screen
import javax.inject.Inject
import com.usacheow.featureotp.R as FeatureR

class OtpFeatureProviderImpl @Inject constructor() : OtpFeatureProvider {

    override fun getOtpFlowDirection(
        args: OtpFeatureProvider.OtpArgs,
    ) = screen(FeatureR.id.sms_code_nav_graph) WITH Bundle().addArgs(args)
}