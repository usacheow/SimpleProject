package com.usacheow.appdemo

import com.usacheow.coreui.fragments.SimpleBottomSheetDialogFragment
import com.usacheow.appdemo.databinding.DialogExampleBottomBinding

class ExampleBottomDialogFragment : SimpleBottomSheetDialogFragment<DialogExampleBottomBinding>() {

    override val params = Params(
        canHide = true,
        needWrapContent = false,
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