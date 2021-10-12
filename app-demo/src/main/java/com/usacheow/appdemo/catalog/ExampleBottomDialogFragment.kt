package com.usacheow.appdemo.catalog

import com.usacheow.appdemo.databinding.DialogExampleBottomBinding
import com.usacheow.coreui.screen.SimpleBottomSheetDialogFragment

class ExampleBottomDialogFragment : SimpleBottomSheetDialogFragment<DialogExampleBottomBinding>() {

    override val defaultParams = Params(
        canHide = true,
        needWrapContent = false,
        needExpand = false,
        middleStatePercent = BottomDialogHeight.HALF_SIZE,
        needMiddleState = true,
        startStatePercent = BottomDialogHeight.QUARTER_SIZE,
        viewBindingProvider = DialogExampleBottomBinding::inflate,
    )

    companion object {
        fun newInstance() = ExampleBottomDialogFragment()
    }
}