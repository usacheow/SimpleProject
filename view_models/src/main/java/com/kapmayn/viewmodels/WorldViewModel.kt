package com.kapmayn.viewmodels

import android.content.res.Resources
import com.kapmayn.core.viewmodels.RxViewModel
import com.kapmayn.domain.commands.GetStubsCommand
import javax.inject.Inject

class WorldViewModel
@Inject constructor(
    private val command: GetStubsCommand,
    private val resources: Resources
) : RxViewModel() {

    val x = 5
}