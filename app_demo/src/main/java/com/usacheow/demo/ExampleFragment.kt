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
        binding.listView.updatePadding(
            bottom = insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom,
        )
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.header.root.apply {
            title = "Demo UiKit"
        }

        binding.fontsScreen.doOnClick { show(FontsFragment.newInstance()) }
        binding.widgetsScreen.doOnClick { show(WidgetsFragment.newInstance()) }
        binding.viewsScreen.doOnClick { show(ViewsFragment.newInstance()) }
        binding.materialDialog.doOnClick { showMaterialDialog() }
        binding.bottomDialog.doOnClick { ExampleBottomDialogFragment.newInstance().show(childFragmentManager, "BOTTOM_FRAGMENT") }
        binding.modalScreen.doOnClick { ExampleModalFragment.newInstance().show(childFragmentManager, "MODAL_FRAGMENT") }
        binding.cameraScreen.doOnClick { show(CameraFragment.newInstance()) }
        binding.calendarListScreen.doOnClick { show(CalendarListFragment.newInstance()) }
        binding.calendarWidgetScreen.doOnClick { show(CalendarWidgetFragment.newInstance()) }
    }

    private fun show(fragment: Fragment) {
        getContainer { show(fragment) }
    }

    private fun showMaterialDialog() {
        messageDialog = MaterialAlertDialogBuilder(requireContext())
                .setBackground(drawable(R.drawable.bg_alert_dialog))
                .setTitle("Material dialog")
                .setMessage("Material dialog example")
                .setPositiveButton("Agree") { _, _ -> }
                .setNegativeButton("Disagree") { _, _ -> }
                .setNeutralButton("Ok") { _, _ -> }
                .create()
                .also { it.show() }
    }
}