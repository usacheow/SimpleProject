package com.usacheow.coreui.base

interface IBackListener {

    /**
    * false - click was not processed
    * true - click was processed
    *
    * */
    fun onBackPressed(): Boolean = false
}