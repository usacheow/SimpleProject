package com.usacheow.simpleapp.mainscreen

import androidx.lifecycle.ViewModel
import com.usacheow.coreui.utils.navigation.MultiStackHistoryManager
import javax.inject.Inject

class BottomBarViewModel
@Inject constructor() : ViewModel() {

    var state: MultiStackHistoryManager.State? = null
}