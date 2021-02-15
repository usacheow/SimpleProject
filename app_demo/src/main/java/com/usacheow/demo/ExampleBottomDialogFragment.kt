package com.usacheow.demo

import com.usacheow.coreui.fragments.SimpleBottomSheetDialogFragment
import com.usacheow.demo.databinding.DialogExampleBottomBinding

class ExampleBottomDialogFragment : SimpleBottomSheetDialogFragment<DialogExampleBottomBinding>() {

    override val params = Params(
        canHide = true,
        needWrapContent = true,
        needExpand = false,
        middleStatePercent = BottomDialogHeight.HALF_SIZE,
        needMiddleState = false,
        startStatePercent = BottomDialogHeight.QUARTER_SIZE,
        viewBindingProvider = DialogExampleBottomBinding::inflate,
    )

    companion object {
        fun newInstance() = ExampleBottomDialogFragment()
    }
}