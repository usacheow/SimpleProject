package com.usacheow.appdemo

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.usacheow.appdemo.catalog.*
import com.usacheow.appdemo.databinding.FragmentDemoBinding
import com.usacheow.appstate.otp.SmsCodeModalFragment
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
import com.usacheow.featureauth.presentation.fragment.PinCodeFragment
import com.usacheow.featureauth.presentation.fragment.SignInFragment
import com.usacheow.featureauth.presentation.fragment.SignInWithPhoneFragment
import com.usacheow.featureauth.presentation.fragment.SignUpFragment
import com.usacheow.featureonboarding.fragment.OnBoardingFragment

private const val CAN_SWIPE_LIST_TO_HIDE = true

class DemoFragment : SimpleFragment<FragmentDemoBinding>() {

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

        binding.listView.adapter = ViewTypesAdapter(listOf(
            HeaderTileItem(TextString("Atoms")),
            BadgeTileItem(needAdaptWidth = false, header = TextString("atom"), value = TextString("1. Fonts"), clickListener = { show(FontsFragment.newInstance()) }),
            BadgeTileItem(needAdaptWidth = false, header = TextString("atom"), value = TextString("2. Buttons"), clickListener = { show(ButtonsFragment.newInstance()) }),
            BadgeTileItem(needAdaptWidth = false, header = TextString("atom"), value = TextString("3. Text Inputs"), clickListener = { show(TextInputsFragment.newInstance()) }),

            HeaderTileItem(TextString("Molecules")),
            BadgeTileItem(needAdaptWidth = false, header = TextString("molecule"), value = TextString("1. Action Tiles"), clickListener = { show(ActionTilesFragment.newInstance()) }),
            BadgeTileItem(needAdaptWidth = false, header = TextString("molecule"), value = TextString("2. List Tiles"), clickListener = { show(ListTilesFragment.newInstance()) }),
            BadgeTileItem(needAdaptWidth = false, header = TextString("molecule"), value = TextString("3. Tag Lists"), clickListener = { show(TagListFragment.newInstance()) }),
            BadgeTileItem(needAdaptWidth = false, header = TextString("molecule"), value = TextString("4. Information Tiles"), clickListener = { show(InformationTilesFragment.newInstance()) }),

            HeaderTileItem(TextString("Organisms")),
            BadgeTileItem(needAdaptWidth = false, header = TextString("organism"), value = TextString("1. Error Message View"), clickListener = { show(ErrorMessageFragment.newInstance()) }),
            BadgeTileItem(needAdaptWidth = false, header = TextString("organism"), value = TextString("2. Num Pad View"), clickListener = { show(NumPadFragment.newInstance()) }),

            HeaderTileItem(TextString("Templates")),
            BadgeTileItem(needAdaptWidth = false, header = TextString("template"), value = TextString("1. Material Dialog"), clickListener = { showMaterialDialog() }),
            BadgeTileItem(needAdaptWidth = false, header = TextString("template"), value = TextString("2. Bottom Dialog"), clickListener = { ExampleBottomDialogFragment.newInstance().show(childFragmentManager, "BOTTOM_FRAGMENT") }),
            BadgeTileItem(needAdaptWidth = false, header = TextString("template"), value = TextString("3. Bottom sheet"), clickListener = { showOrHideBottomSheet() }),
            BadgeTileItem(needAdaptWidth = false, header = TextString("template"), value = TextString("4. Modal Fragment"), clickListener = { ExampleModalFragment.newInstance().show(childFragmentManager, "MODAL_FRAGMENT") }),
            BadgeTileItem(needAdaptWidth = false, header = TextString("template"), value = TextString("5. Onboarding Fragment"), clickListener = { show(
                OnBoardingFragment.newInstance()) }),

            HeaderTileItem(TextString("Pages")),
            BadgeTileItem(needAdaptWidth = false, header = TextString("page"), value = TextString("1. SMS Code Fragment"), clickListener = { SmsCodeModalFragment.newInstance(4).show(childFragmentManager, "SMS_CODE") }),
            BadgeTileItem(needAdaptWidth = false, header = TextString("page"), value = TextString("2. Sign Up Fragment"), clickListener = { show(SignUpFragment.newInstance()) }),
            BadgeTileItem(needAdaptWidth = false, header = TextString("page"), value = TextString("3. Sign In Fragment"), clickListener = { show(SignInFragment.newInstance()) }),
            BadgeTileItem(needAdaptWidth = false, header = TextString("page"), value = TextString("4. Sign In With Phone Fragment"), clickListener = { show(SignInWithPhoneFragment.newInstance()) }),
            BadgeTileItem(needAdaptWidth = false, header = TextString("page"), value = TextString("5. Pin Code Fragment"), clickListener = { show(PinCodeFragment.newInstance()) }),
        ))
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

    private fun show(fragment: Fragment) {
        getContainer { navigateTo(fragment) }
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

    private fun showOrHideBottomSheet() = with (binding.bottomSheetLayout) {
        when {
            isVisible -> setHiddenState()
            else -> setCollapseState()
        }
    }
}