package com.usacheow.appdemo

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.usacheow.coreui.fragments.SimpleFragment
import com.usacheow.coreui.uikit.SimpleBottomSheetLayout
import com.usacheow.coreui.utils.view.*
import com.usacheow.appdemo.databinding.FragmentExampleBinding

private const val CAN_SWIPE_LIST_TO_HIDE = true

class ExampleFragment : SimpleFragment<FragmentExampleBinding>() {

    override val params = Params(
        viewBindingProvider = FragmentExampleBinding::inflate,
    )

    companion object {
        fun newInstance() = ExampleFragment()
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue) {
        binding.header.toolbar.post {
            binding.bottomSheetLayout.setExpandOffset(
                binding.header.toolbar.height + insets.getInsets(WindowInsetsCompat.Type.systemBars()).top
            )
        }
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
        binding.bottomSheet.doOnClick { binding.bottomSheetLayout.setCollapseState() }
        binding.modalScreen.doOnClick { ExampleModalFragment.newInstance().show(childFragmentManager, "MODAL_FRAGMENT") }
        binding.cameraScreen.doOnClick { show(CameraFragment.newInstance()) }
        binding.calendarListScreen.doOnClick { show(CalendarListFragment.newInstance()) }
        binding.calendarWidgetScreen.doOnClick { show(CalendarWidgetFragment.newInstance()) }

        binding.bottomSheetLayout.setHiddenState()
        binding.bottomSheetLayout.setup(
            SimpleBottomSheetLayout.BottomSheetHeight.QUARTER_SIZE,
            CAN_SWIPE_LIST_TO_HIDE,
            onStateChangedListener = { newState ->
                when (newState) {
                    BottomSheetBehavior.STATE_HIDDEN -> {
                        binding.bottomSheet.isEnabled = true
                        binding.bottomSheet.setTextColor(color(R.color.colorText))
                    }

                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        binding.bottomSheet.isEnabled = false
                        binding.bottomSheet.setTextColor(color(R.color.disabled))
                        binding.header.root.setExpanded(false)
                    }

                    BottomSheetBehavior.STATE_EXPANDED -> Unit
                }
            },
            onScrollListener = {
                if (it >= 0) {
                    binding.buttonsLayout.translationY = -it * 50.toPx
                }
                binding.buttonsLayout.alpha = 1 - it
            }
        )
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