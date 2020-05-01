package com.usacheow.simpleapp.mainscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.usacheow.coreuikit.activity.ContainerActivity
import com.usacheow.diprovider.DiProvider
import com.usacheow.featureauth.presentation.fragment.AuthContainerFragment
import com.usacheow.featurehello.presentation.fragment.HelloContainerFragment

class MainScreenContainerActivity : ContainerActivity() {

    override val INIT_FRAGMENT_TAG_KEY = MainScreenContainerActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.setFragmentResultListener(AuthContainerFragment.REQUEST_KEY, this) { _, bundle ->
            val isAuthSuccess = bundle.getBoolean(AuthContainerFragment.IS_AUTH_SUCCESS)

            if (isAuthSuccess) {
                show(HelloContainerFragment.newInstance(), false)
            }
        }
    }

    override fun getInitFragment(): Fragment {
//        return AuthContainerFragment.newInstance()
        return HelloContainerFragment.newInstance()
//        return PinCodeFragment.newInstance()
//        return ExampleContainerFragment.newInstance()
    }

    override fun inject(diProvider: DiProvider) {}
}