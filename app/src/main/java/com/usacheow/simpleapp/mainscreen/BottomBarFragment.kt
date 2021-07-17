package com.usacheow.simpleapp.mainscreen

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.core.graphics.Insets
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.usacheow.coreui.fragment.SimpleFragment
import com.usacheow.coreui.utils.view.PaddingValue
import com.usacheow.coreui.utils.view.doOnApplyWindowInsets
import com.usacheow.coreui.utils.view.getBottomInset
import com.usacheow.coreui.utils.view.hideIme
import com.usacheow.simpleapp.R
import com.usacheow.simpleapp.databinding.FragmentBottomBarBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.max

@AndroidEntryPoint
class BottomBarFragment : SimpleFragment<FragmentBottomBarBinding>() {

    override val params = Params(
        viewBindingProvider = FragmentBottomBarBinding::inflate,
    )

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            isEnabled = navController.popBackStack()
        }
    }

    private val navController by lazy {
        (childFragmentManager.findFragmentById(R.id.bottomBarContainerLayout) as NavHostFragment).navController
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue): WindowInsetsCompat {
        binding.appBottomBar.updatePadding(bottom = insets.getBottomInset())

        val imeInset = Insets.of(
            insets.getInsets(WindowInsetsCompat.Type.ime()).left,
            insets.getInsets(WindowInsetsCompat.Type.ime()).top,
            insets.getInsets(WindowInsetsCompat.Type.ime()).right,
            max(insets.getInsets(WindowInsetsCompat.Type.ime()).bottom - binding.appBottomBar.height, 0),
        )
        return WindowInsetsCompat.Builder(insets).setInsets(WindowInsetsCompat.Type.ime(), imeInset).build()
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        requireActivity().onBackPressedDispatcher.addCallback(onBackPressedCallback)
        binding.appBottomBar.setupWithNavController(navController)
        binding.appBottomBar.doOnApplyWindowInsets { insets, _ -> insets }

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            windowInsetsController?.hideIme()
            onBackPressedCallback.isEnabled = binding.appBottomBar.selectedItemId != R.id.main_nav_graph
        }
    }
}