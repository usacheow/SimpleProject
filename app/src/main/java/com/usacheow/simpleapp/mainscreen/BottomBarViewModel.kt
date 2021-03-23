package com.usacheow.simpleapp.mainscreen

import com.usacheow.coreui.utils.navigation.MultiStackHistoryManager
import com.usacheow.coreui.viewmodel.SimpleViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BottomBarViewModel @Inject constructor() : SimpleViewModel() {

    var state: MultiStackHistoryManager.State? = null
}