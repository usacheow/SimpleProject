package com.usacheow.appdemo

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.usacheow.appdemo.databinding.FragmentDemoBinding
import com.usacheow.coreui.adapter.ViewTypesAdapter
import com.usacheow.coreui.fragment.SimpleFragment
import com.usacheow.coreui.uikit.molecule.BadgeTileItem
import com.usacheow.coreui.uikit.molecule.HeaderTileItem
import com.usacheow.coreui.uikit.template.SimpleBottomSheetLayout
import com.usacheow.coreui.utils.TextString
import com.usacheow.coreui.utils.view.PaddingValue
import com.usacheow.coreui.utils.view.getBottomInset
import com.usacheow.coreui.utils.view.getTopInset
import com.usacheow.coreui.utils.view.toPx
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val CAN_SWIPE_LIST_TO_HIDE = true

@AndroidEntryPoint
class DemoFragment : SimpleFragment<FragmentDemoBinding>() {

    @Inject lateinit var router: DemoRouter

    override val params = Params(
        viewBindingProvider = FragmentDemoBinding::inflate,
    )

    companion object {
        fun newInstance() = DemoFragment()
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue): WindowInsetsCompat {
        binding.header.toolbar.post {
            binding.bottomSheetLayout.setExpandOffset(binding.header.toolbar.height + insets.getTopInset())
        }
        binding.header.root.applyInsets(insets.getTopInset())
        binding.listView.updatePadding(bottom = insets.getBottomInset())
        return insets
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.header.root.apply {
            title = "Demo UIkit"
        }

        binding.listView.adapter = ViewTypesAdapter(
            listOf(
                HeaderTileItem(TextString("Atoms")),
                BadgeTileItem(
                    needAdaptWidth = false,
                    header = TextString("atom"),
                    value = TextString("1. Fonts"),
                    clickListener = { router.fromDemoToFontsScreen() },
                ),
                BadgeTileItem(
                    needAdaptWidth = false,
                    header = TextString("atom"),
                    value = TextString("2. Buttons"),
                    clickListener = { router.fromDemoToButtonsScreen() },
                ),
                BadgeTileItem(
                    needAdaptWidth = false,
                    header = TextString("atom"),
                    value = TextString("3. Text Inputs"),
                    clickListener = { router.fromDemoToTextInputsScreen() },
                ),

                HeaderTileItem(TextString("Molecules")),
                BadgeTileItem(
                    needAdaptWidth = false,
                    header = TextString("molecule"),
                    value = TextString("1. Action Tiles"),
                    clickListener = { router.fromDemoToActionTilesScreen() },
                ),
                BadgeTileItem(
                    needAdaptWidth = false,
                    header = TextString("molecule"),
                    value = TextString("2. List Tiles"),
                    clickListener = { router.fromDemoToListTilesScreen() },
                ),
                BadgeTileItem(
                    needAdaptWidth = false,
                    header = TextString("molecule"),
                    value = TextString("3. Tag Lists"),
                    clickListener = { router.fromDemoToTagListScreen() },
                ),
                BadgeTileItem(
                    needAdaptWidth = false,
                    header = TextString("molecule"),
                    value = TextString("4. Information Tiles"),
                    clickListener = { router.fromDemoToInformationTilesScreen() },
                ),

                HeaderTileItem(TextString("Organisms")),
                BadgeTileItem(
                    needAdaptWidth = false,
                    header = TextString("organism"),
                    value = TextString("1. Error Message View"),
                    clickListener = { router.fromDemoToErrorMessageScreen() },
                ),
                BadgeTileItem(
                    needAdaptWidth = false,
                    header = TextString("organism"),
                    value = TextString("2. Num Pad View"),
                    clickListener = { router.fromDemoToNumPadScreen() },
                ),

                HeaderTileItem(TextString("Templates")),
                BadgeTileItem(
                    needAdaptWidth = false,
                    header = TextString("template"),
                    value = TextString("1. Material Dialog"),
                    clickListener = { showMaterialDialog() },
                ),
                BadgeTileItem(
                    needAdaptWidth = false,
                    header = TextString("template"),
                    value = TextString("2. Bottom Dialog"),
                    clickListener = { router.fromDemoToExampleBottomDialogScreen() },
                ),
                BadgeTileItem(
                    needAdaptWidth = false,
                    header = TextString("template"),
                    value = TextString("3. Bottom sheet"),
                    clickListener = { showOrHideBottomSheet() },
                ),
                BadgeTileItem(
                    needAdaptWidth = false,
                    header = TextString("template"),
                    value = TextString("4. Modal Fragment"),
                    clickListener = { router.fromDemoToExampleModalScreen() },
                ),
                BadgeTileItem(
                    needAdaptWidth = false,
                    header = TextString("template"),
                    value = TextString("5. Onboarding Fragment"),
                    clickListener = { router.toOnBoardingFlow() },
                ),

                HeaderTileItem(TextString("Pages")),
                BadgeTileItem(
                    needAdaptWidth = false,
                    header = TextString("page"),
                    value = TextString("1. SMS Code Fragment"),
                    clickListener = { router.toSmsCodeFlow(codeLength = 2) },
                ),
                BadgeTileItem(
                    needAdaptWidth = false,
                    header = TextString("page"),
                    value = TextString("2. Sign Up Fragment"),
                    clickListener = { router.toSignUpFlow() },
                ),
                BadgeTileItem(
                    needAdaptWidth = false,
                    header = TextString("page"),
                    value = TextString("3. Sign In Fragment"),
                    clickListener = { router.toSignInFlow() },
                ),
                BadgeTileItem(
                    needAdaptWidth = false,
                    header = TextString("page"),
                    value = TextString("4. Sign In With Phone Fragment"),
                    clickListener = { router.toSignInWithPhoneFlow() },
                ),
                BadgeTileItem(
                    needAdaptWidth = false,
                    header = TextString("page"),
                    value = TextString("5. Pin Code Fragment"),
                    clickListener = { router.toPinCodeFlow() },
                ),
            )
        )
        binding.listView.layoutManager = GridLayoutManager(requireContext(), 2).apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int = when {
                    (binding.listView.adapter as ViewTypesAdapter).getData()[position] is BadgeTileItem -> 1

                    else -> 2
                }
            }
        }

        setupBottomSheet()
    }

    private fun setupBottomSheet() {
        binding.bottomSheetLayout.setHiddenState()
        binding.bottomSheetLayout.setup(
            SimpleBottomSheetLayout.BottomSheetHeight.QUARTER_SIZE,
            CAN_SWIPE_LIST_TO_HIDE,
            onStateChangedListener = { newState ->
                when (newState) {
                    BottomSheetBehavior.STATE_HIDDEN -> {
                    }

                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        binding.header.root.setExpanded(false)
                    }

                    BottomSheetBehavior.STATE_EXPANDED -> Unit
                }
            },
            onScrollListener = {
                if (it >= 0) {
                    binding.listView.translationY = -it * 50.toPx
                }
                binding.listView.alpha = 1 - it
            }
        )
    }

    private fun showMaterialDialog() {
        messageDialog = MaterialAlertDialogBuilder(requireContext())
//                .setBackground(drawable(R.drawable.bg_alert_dialog))
            .setTitle("Material dialog")
            .setMessage("Material dialog example")
            .setPositiveButton("Agree") { _, _ -> }
            .setNegativeButton("Disagree") { _, _ -> }
            .setNeutralButton("Ok") { _, _ -> }
            .create()
            .also { it.show() }
    }

    private fun showOrHideBottomSheet() = with(binding.bottomSheetLayout) {
        when {
            isVisible -> setHiddenState()
            else -> setCollapseState()
        }
    }
}