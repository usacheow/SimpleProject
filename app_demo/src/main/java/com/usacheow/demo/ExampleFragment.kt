package com.usacheow.demo

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.usacheow.coreui.fragments.SimpleFragment
import com.usacheow.coreui.utils.view.PaddingValue
import com.usacheow.coreui.utils.view.doOnClick
import com.usacheow.coreui.utils.view.drawable
import com.usacheow.demo.databinding.FragmentExampleBinding

class ExampleFragment : SimpleFragment<FragmentExampleBinding>() {

    override val params = Params(
        viewBindingProvider = FragmentExampleBinding::inflate,
    )

    companion object {
        fun newInstance() = ExampleFragment()
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue) {
        binding.listView.updatePadding(bottom = insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom)
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.header.root.apply {
            title = "Demo UiKit"
        }

        binding.fontsScreen.doOnClick { show(FontsFragment.newInstance()) }
        binding.widgetsScreen.doOnClick { show(WidgetsFragment.newInstance()) }
        binding.viewsScreen.doOnClick { show(ViewsFragment.newInstance()) }
        binding.materialDialog.doOnClick { showMaterialDialog() }
//        binding.bottomDialog.doOnClick { showBottomDialog() }
//        binding.modalScreen.doOnClick { showModalScreen() }
        binding.calendarScreen.doOnClick { show(CalendarFragment.newInstance()) }
        binding.cameraScreen.doOnClick { show(CameraFragment.newInstance()) }
    }

    private fun show(fragment: Fragment) {
        getContainer { show(fragment) }
    }

    private fun showMaterialDialog() {
        messageDialog = MaterialAlertDialogBuilder(requireContext())
                .setBackground(drawable(R.drawable.bg_alert_dialog))
                .setTitle("Lorem ipsum")
                .setMessage("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua")
                .setPositiveButton("Agree") { _, _ -> }
                .setNegativeButton("Disagree") { _, _ -> }
                .setNeutralButton("Ok") { _, _ -> }
                .create()
                .also { it.show() }
    }

    private fun showBottomDialog() {

    }

    private fun showModalScreen() {

    }
}