package com.usacheow.appshared

import com.usacheow.coredata.database.Storage
import com.usacheow.coreui.billing.BillingWrapper
import com.usacheow.coreui.billing.SimpleBillingImpl
import com.usacheow.coreui.utils.SimpleAction
import com.usacheow.coreui.viewmodel.SimpleViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class PurchaseStateViewModel @Inject constructor(
    private val storage: Storage,
    private val billingWrapper: BillingWrapper,
) : SimpleViewModel() {

    private val _purchaseState = MutableStateFlow<Boolean>(storage.isPayedVersion)
    val purchaseState = _purchaseState.asStateFlow()

    private val _openPurchaseScreenAction = Channel<SimpleAction>()
    val openPurchaseScreenAction = _openPurchaseScreenAction.receiveAsFlow()

}