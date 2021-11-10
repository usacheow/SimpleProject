package com.usacheow.apptest

import android.os.Bundle
import android.widget.Toast
import androidx.biometric.BiometricPrompt
import com.usacheow.apptest.databinding.Fragment1Binding
import com.usacheow.coredata.crypto.CryptoConfigurator
import com.usacheow.coreui.helper.biometric.BiometricData
import com.usacheow.coreui.helper.biometric.BiometricEnterManager
import com.usacheow.coreui.screen.SimpleFragment
import com.usacheow.coreui.uikit.helper.doOnClick
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class Fragment1Fragment : SimpleFragment<Fragment1Binding>() {

    override val defaultParams = Params(
        viewBindingProvider = Fragment1Binding::inflate,
    )

    @Inject lateinit var router: TestRouter

    @Inject lateinit var biometricDelegate: BiometricEnterManager
    private val cryptoConfig by lazy { CryptoConfigurator(requireContext()) }
    var cryptoObject: BiometricPrompt.CryptoObject? = null
    private val secretWord = "Secret word"
    private var encoded = "nothing"

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.goButton.doOnClick(router::from1To2Screen)
        binding.backButton.doOnClick(router::back)

        binding.encodeButton.doOnClick {
            cryptoObject = cryptoConfig.createCryptoObject()
            encoded = cryptoConfig.encode(secretWord) ?: "encode error"
            Toast.makeText(requireContext(), "$encoded", Toast.LENGTH_SHORT).show()
        }
        binding.decodeButton.doOnClick {
            biometricDelegate.tryShow(BiometricData(cryptoObject!!))
        }

        biometricDelegate.onSuccessAction = {
            val decoded = cryptoConfig.decode(encoded, it?.cipher!!) ?: "decode 1 error"
            Toast.makeText(requireContext(), "$decoded", Toast.LENGTH_SHORT).show()
        }
    }
}