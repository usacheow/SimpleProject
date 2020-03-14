package com.kapmayn.coreuikit.fragments

import android.app.Dialog
import android.os.Bundle
import com.kapmayn.coreuikit.R

abstract class SimpleModalFragment : SimpleDialogFragment() {

    abstract fun onBackPressedAggregation()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dialog?.window?.attributes?.windowAnimations = R.style.DialogAnimation
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return object : Dialog(requireContext(), theme) {
            override fun onBackPressed() {
                onBackPressedAggregation()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.DialogAnimation)
        setHasOptionsMenu(true)
    }
}