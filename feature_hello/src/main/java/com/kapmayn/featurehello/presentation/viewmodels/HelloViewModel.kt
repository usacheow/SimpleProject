package com.kapmayn.featurehello.presentation.viewmodels

import android.content.res.Resources
import com.kapmayn.core.presentation.viewmodels.SimpleRxViewModel
import com.kapmayn.featurehello.domain.commands.GetStubsCommand
import javax.inject.Inject

class HelloViewModel
@Inject constructor(
    private val command: GetStubsCommand,
    private val resources: Resources
) : SimpleRxViewModel() {

    val x = 5
}