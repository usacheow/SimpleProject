package com.usacheow.simpleapp

import com.usacheow.core.BuildInfo
import com.usacheow.coremediator.AuthorizationFeatureProvider
import com.usacheow.coremediator.BottomBarFeatureProvider
import com.usacheow.coremediator.MainFeatureProvider
import com.usacheow.coremediator.OnBoardingFeatureProvider
import com.usacheow.coremediator.OtpFeatureProvider
import com.usacheow.coremediator.PurchaseFeatureProvider
import com.usacheow.featureauth.presentation.navigation.AuthorizationFeatureProviderImpl
import com.usacheow.featurebottombar.BottomBarFeatureProviderImpl
import com.usacheow.featuremain.presentation.navigation.MainFeatureProviderImpl
import com.usacheow.featureonboarding.navigation.OnBoardingFeatureProviderImpl
import com.usacheow.featureotp.navigation.OtpFeatureProviderImpl
import com.usacheow.featurepurchase.navigation.PurchaseFeatureProviderImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class BuildInfoModule {

    @Provides
    @Singleton
    fun buildInfo() = BuildInfo(
        applicationId = BuildConfig.APPLICATION_ID,
        buildType = BuildConfig.BUILD_TYPE,
        versionCode = BuildConfig.VERSION_CODE,
        versionName = BuildConfig.VERSION_NAME,
    )
}