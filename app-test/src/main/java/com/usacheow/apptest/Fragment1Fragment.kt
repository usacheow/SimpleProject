package com.usacheow.apptest

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import com.usacheow.apptest.databinding.Fragment1Binding
import com.usacheow.coredata.crypto.CryptoConfigurator
import com.usacheow.coreui.screen.SimpleFragment
import com.usacheow.coreuiview.helper.doOnClick
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@SuppressLint("NewApi")
@AndroidEntryPoint
class Fragment1Fragment : SimpleFragment<Fragment1Binding>() {

    override val defaultParams = Params(
        viewBindingProvider = Fragment1Binding::inflate,
    )

    @Inject lateinit var router: TestRouter

//    @Inject lateinit var biometricDelegate: BiometricEnterManager
//    private val cryptoConfig by lazy { CryptoConfigurator() }
//    private val secretWord = "Secret word"
//    private var encoded = "nothing"

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.goButton.doOnClick(router::from1To2Screen)
        binding.backButton.doOnClick(router::back)

//        binding.encodeButton.doOnClick {
//            val cipher = CryptoConfigurator().createEncryptCipher()
//            encoded = cryptoConfig.encode(secretWord, cipher!!)
//
//            Toast.makeText(requireContext(), encoded, Toast.LENGTH_SHORT).show()
//        }
//        binding.decodeButton.doOnClick {
//            val cipher = CryptoConfigurator().createDecryptCipher()
//            biometricDelegate.tryShow(BiometricData(cipher!!))
//        }
//
//        biometricDelegate.onSuccessAction = {
//            val decoded = CryptoConfigurator().decode(encoded, it?.cipher!!)
//            Toast.makeText(requireContext(), decoded, Toast.LENGTH_SHORT).show()
//        }
    }
}