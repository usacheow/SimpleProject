package com.usacheow.appdemo

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.usacheow.appdemo.databinding.FragmentDemoBinding
import com.usacheow.core.resource.TextSource
import com.usacheow.core.resource.toTextSource
import com.usacheow.corenavigation.OnBoardingFeatureProvider
import com.usacheow.coreui.adapter.ViewStateAdapter
import com.usacheow.coreui.screen.SimpleFragment
import com.usacheow.coreui.uikit.helper.PaddingValue
import com.usacheow.coreui.uikit.helper.applyBottomInset
import com.usacheow.coreui.uikit.helper.applyTopInset
import com.usacheow.coreui.uikit.helper.getBottomInset
import com.usacheow.coreui.uikit.helper.getTopInset
import com.usacheow.coreui.uikit.helper.toPx
import com.usacheow.coreui.uikit.molecule.BadgeTileItem
import com.usacheow.coreui.uikit.molecule.HeaderTileItem
import com.usacheow.coreui.uikit.template.SimpleBottomSheetLayout
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import com.usacheow.core.R as CoreR
import com.usacheow.coreui.R as CoreUiR

private const val CAN_SWIPE_LIST_TO_HIDE = true

@AndroidEntryPoint
class DemoFragment : SimpleFragment<FragmentDemoBinding>() {

    @Inject lateinit var router: DemoRouter

    override val defaultParams = Params(
        viewBindingProvider = FragmentDemoBinding::inflate,
    )

    private val onBoardingArgs by lazy {
        OnBoardingFeatureProvider.OnBoardingArgs(
            mutableListOf(
                OnBoardingFeatureProvider.OnBoardingArgs.Page(
                    defaultImageRes = CoreUiR.drawable.ic_user,
                    title = res.getString(CoreR.string.on_boarding_title_1).toTextSource(),
                    description = res.getString(CoreR.string.on_boarding_description_1).toTextSource()
                ),
                OnBoardingFeatureProvider.OnBoardingArgs.Page(
                    defaultImageRes = CoreUiR.drawable.ic_user,
                    title = res.getString(CoreR.string.on_boarding_title_2).toTextSource(),
                    description = res.getString(CoreR.string.on_boarding_description_2).toTextSource()
                ),
                OnBoardingFeatureProvider.OnBoardingArgs.Page(
                    defaultImageRes = CoreUiR.drawable.ic_user,
                    title = res.getString(CoreR.string.on_boarding_title_3).toTextSource(),
                    description = res.getString(CoreR.string.on_boarding_description_3).toTextSource()
                )
            )
        )
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue): WindowInsetsCompat {
        binding.header.post {
            binding.bottomSheetLayout.setExpandOffset(binding.header.height)
        }
        binding.header.applyTopInset(insets.getTopInset())
        binding.listView.applyBottomInset(insets.getBottomInset())
        return insets
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.listView.adapter = ViewStateAdapter(
            listOf(
                HeaderTileItem(TextSource.Simple("Atoms")),
                BadgeTileItem(
                    needAdaptWidth = false,
                    header = TextSource.Simple("atom"),
                    value = TextSource.Simple("1. Typography"),
                    clickListener = { router.fromDemoToTypographyScreen() },
                ),
                BadgeTileItem(
                    needAdaptWidth = false,
                    header = TextSource.Simple("atom"),
                    value = TextSource.Simple("2. Palette"),
                    clickListener = { router.fromDemoToPaletteScreen() },
                ),
                BadgeTileItem(
                    needAdaptWidth = false,
                    header = TextSource.Simple("atom"),
                    value = TextSource.Simple("3. Buttons"),
                    clickListener = { router.fromDemoToButtonsScreen() },
                ),
                BadgeTileItem(
                    needAdaptWidth = false,
                    header = TextSource.Simple("atom"),
                    value = TextSource.Simple("4. Text Inputs"),
                    clickListener = { router.fromDemoToTextInputsScreen() },
                ),

                HeaderTileItem(TextSource.Simple("Molecules")),
                BadgeTileItem(
                    needAdaptWidth = false,
                    header = TextSource.Simple("molecule"),
                    value = TextSource.Simple("1. Cell Tiles"),
                    clickListener = { router.fromDemoToActionTilesScreen() },
                ),
                BadgeTileItem(
                    needAdaptWidth = false,
                    header = TextSource.Simple("molecule"),
                    value = TextSource.Simple("2. List Tiles"),
                    clickListener = { router.fromDemoToListTilesScreen() },
                ),
                BadgeTileItem(
                    needAdaptWidth = false,
                    header = TextSource.Simple("molecule"),
                    value = TextSource.Simple("3. Tag Lists"),
                    clickListener = { router.fromDemoToTagListScreen() },
                ),
                BadgeTileItem(
                    needAdaptWidth = false,
                    header = TextSource.Simple("molecule"),
                    value = TextSource.Simple("4. Information Tiles"),
                    clickListener = { router.fromDemoToInformationTilesScreen() },
                ),

                HeaderTileItem(TextSource.Simple("Organisms")),
                BadgeTileItem(
                    needAdaptWidth = false,
                    header = TextSource.Simple("organism"),
                    value = TextSource.Simple("1. Message Tiles"),
                    clickListener = { router.fromDemoToMessageScreen() },
                ),
                BadgeTileItem(
                    needAdaptWidth = false,
                    header = TextSource.Simple("organism"),
                    value = TextSource.Simple("2. Num Pad View"),
                    clickListener = { router.fromDemoToNumPadScreen() },
                ),

                HeaderTileItem(TextSource.Simple("Templates")),
                BadgeTileItem(
                    needAdaptWidth = false,
                    header = TextSource.Simple("template"),
                    value = TextSource.Simple("1. Alert Dialog"),
                    clickListener = { showMaterialDialog() },
                ),
                BadgeTileItem(
                    needAdaptWidth = false,
                    header = TextSource.Simple("template"),
                    value = TextSource.Simple("2. Single Choice Dialog"),
                    clickListener = { showMaterialDialogWithSingleChoice() },
                ),
                BadgeTileItem(
                    needAdaptWidth = false,
                    header = TextSource.Simple("template"),
                    value = TextSource.Simple("3. Multi Choice Dialog"),
                    clickListener = { showMaterialDialogWithMultiChoice() },
                ),
                BadgeTileItem(
                    needAdaptWidth = false,
                    header = TextSource.Simple("template"),
                    value = TextSource.Simple("4. Modal Fragment"),
                    clickListener = { router.fromDemoToExampleModalScreen() },
                ),
                BadgeTileItem(
                    needAdaptWidth = false,
                    header = TextSource.Simple("template"),
                    value = TextSource.Simple("5. Bottom Dialog"),
                    clickListener = { router.fromDemoToExampleBottomDialogScreen() },
                ),
                BadgeTileItem(
                    needAdaptWidth = false,
                    header = TextSource.Simple("template"),
                    value = TextSource.Simple("6. Bottom sheet"),
                    clickListener = { showOrHideBottomSheet() },
                ),
                BadgeTileItem(
                    needAdaptWidth = false,
                    header = TextSource.Simple("template"),
                    value = TextSource.Simple("7. Onboarding Fragment"),
                    clickListener = { router.toOnBoardingFlow(onBoardingArgs) },
                ),

                HeaderTileItem(TextSource.Simple("Pages")),
                BadgeTileItem(
                    needAdaptWidth = false,
                    header = TextSource.Simple("page"),
                    value = TextSource.Simple("1. SMS Code Fragment"),
                    clickListener = { router.toSmsCodeFlow(codeLength = 2) },
                ),
                BadgeTileItem(
                    needAdaptWidth = false,
                    header = TextSource.Simple("page"),
                    value = TextSource.Simple("2. Sign Up Fragment"),
                    clickListener = { router.toSignUpFlow() },
                ),
                BadgeTileItem(
                    needAdaptWidth = false,
                    header = TextSource.Simple("page"),
                    value = TextSource.Simple("3. Sign In Fragment"),
                    clickListener = { router.toSignInFlow() },
                ),
                BadgeTileItem(
                    needAdaptWidth = false,
                    header = TextSource.Simple("page"),
                    value = TextSource.Simple("4. Sign In With Phone Fragment"),
                    clickListener = { router.toSignInWithPhoneFlow() },
                ),
                BadgeTileItem(
                    needAdaptWidth = false,
                    header = TextSource.Simple("page"),
                    value = TextSource.Simple("5. Pin Code Fragment"),
                    clickListener = { router.toPinCodeFlow() },
                ),
            )
        )
        binding.listView.layoutManager = GridLayoutManager(requireContext(), 2).apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int = when {
                    (binding.listView.adapter as ViewStateAdapter).getData()[position] is BadgeTileItem -> 1

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
                        binding.header.setExpanded(false)
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
            .setTitle("Material dialog")
            .setMessage("Material dialog example")
            .setPositiveButton("Agree") { _, _ -> }
            .setNegativeButton("Disagree") { _, _ -> }
            .setNeutralButton("Ok") { _, _ -> }
            .create()
            .also { it.show() }
    }

    private fun showMaterialDialogWithSingleChoice() {
        messageDialog = MaterialAlertDialogBuilder(requireContext())
            .setTitle("Single choice dialog")
            .setSingleChoiceItems(arrayOf("Item 1", "Item 2", "Item 3"), 0) { _, _ -> }
            .setPositiveButton("Agree") { _, _ -> }
            .setNegativeButton("Disagree") { _, _ -> }
            .create()
            .also { it.show() }
    }

    private fun showMaterialDialogWithMultiChoice() {
        messageDialog = MaterialAlertDialogBuilder(requireContext())
            .setTitle("Multi choice dialog")
            .setMultiChoiceItems(arrayOf("Item 1", "Item 2", "Item 3"), booleanArrayOf(true, false, false)) { _, _, _ -> }
            .setPositiveButton("Agree") { _, _ -> }
            .setNegativeButton("Disagree") { _, _ -> }
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