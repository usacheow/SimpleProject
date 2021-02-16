package com.usacheow.simpleapp.mainscreen

import androidx.lifecycle.ViewModel
import com.usacheow.coreui.utils.navigation.MultiStackHistoryManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BottomBarViewModel @Inject constructor() : ViewModel() {

    var state: MultiStackHistoryManager.State? = null
}