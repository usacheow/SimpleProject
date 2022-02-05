package com.usacheow.apptest

import androidx.fragment.app.Fragment
import com.usacheow.coreui.navigation.Router
import com.usacheow.coreui.navigation.OPEN_IN
import com.usacheow.coreui.navigation.REPLACING
import com.usacheow.coreui.navigation.notInclusive
import com.usacheow.coreui.navigation.screen
import javax.inject.Inject
import com.usacheow.apptest.R as TestAppR

class TestRouter @Inject constructor(fragment: Fragment) : Router(fragment) {

    fun from1To2Screen() {
        screen(TestAppR.id.action_fragment1Fragment_to_fragment2Fragment) OPEN_IN navController
    }

    fun from2To3Screen() {
        screen(TestAppR.id.action_fragment2Fragment_to_fragment3Fragment) OPEN_IN navController
    }

    fun from3To4Screen() {
        screen(TestAppR.id.action_fragment3Fragment_to_fragment4Fragment) OPEN_IN navController
    }

    fun from3To6Screen() {
        screen(TestAppR.id.action_fragment3Fragment_to_fragment6Fragment) OPEN_IN navController
    }

    fun from4To5Screen() {
        screen(TestAppR.id.action_fragment4Fragment_to_fragment5Fragment) OPEN_IN navController
    }

    fun from5To7Screen() {
        screen(TestAppR.id.fragment7Fragment) REPLACING TestAppR.id.fragment4Fragment OPEN_IN navController
    }

    fun from6To7Screen() {
        screen(TestAppR.id.action_fragment6Fragment_to_fragment7Fragment) REPLACING
            notInclusive(TestAppR.id.fragment3Fragment) OPEN_IN navController
    }

    fun backTo2Screen() {
        backTo(TestAppR.id.fragment2Fragment)
    }

    fun backTo4Screen() {
        backTo(TestAppR.id.fragment4Fragment)
    }

    fun backTo6Screen() {
        backTo(TestAppR.id.fragment6Fragment)
    }
}