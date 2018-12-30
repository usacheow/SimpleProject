package com.kapmayn.viewmodels

import com.kapmayn.core.viewmodels.RxViewModel
import com.kapmayn.domain.commands.GetStubsCommand
import javax.inject.Inject

class HelloViewModel
@Inject constructor(
    private var command: GetStubsCommand
) : RxViewModel() {

    val x = 5
}