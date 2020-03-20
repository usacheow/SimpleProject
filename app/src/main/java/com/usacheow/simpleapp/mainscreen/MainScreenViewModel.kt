package com.usacheow.simpleapp.mainscreen

import androidx.lifecycle.ViewModel
import com.usacheow.coreuikit.utils.BottomBarHistoryManager
import javax.inject.Inject

class MainScreenViewModel
@Inject constructor() : ViewModel() {

    var state: BottomBarHistoryManager.State? = null
}