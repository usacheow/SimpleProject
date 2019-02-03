package com.kapmayn.featurehello.presentation.viewmodels

import android.content.res.Resources
import com.kapmayn.core.presentation.viewmodels.RxViewModel
import com.kapmayn.featurehello.domain.commands.GetStubsCommand
import javax.inject.Inject

class HelloViewModel
@Inject constructor(
    private val command: GetStubsCommand,
    private val resources: Resources
) : RxViewModel() {

    val x = 5
}