package com.usacheow.apptest

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.usacheow.coreui.base.Router
import com.usacheow.coreui.utils.navigation.openIn
import com.usacheow.coreui.utils.navigation.replaceAllTo
import com.usacheow.coreui.utils.navigation.screen
import com.usacheow.coreui.utils.navigation.toFeatureNavDirection
import javax.inject.Inject

class TestRouter @Inject constructor(fragment: Fragment) : Router(fragment) {

    fun from1To2Screen() {
        screen(R.id.action_fragment1Fragment_to_fragment2Fragment)
            .openIn(navController)
    }

    fun from2To3Screen() {
        screen(R.id.action_fragment2Fragment_to_fragment3Fragment)
            .openIn(navController)
    }

    fun from3To4Screen() {
            screen(R.id.action_fragment3Fragment_to_fragment4Fragment)
            .openIn(navController)
    }

    fun from3To6Screen() {
            screen(R.id.action_fragment3Fragment_to_fragment6Fragment)
            .openIn(navController)
    }

    fun from4To5Screen() {
        screen(R.id.action_fragment4Fragment_to_fragment5Fragment)
            .openIn(navController)
    }

    fun from5To7Screen() {
        screen(R.id.fragment7Fragment)
            .replaceAllTo(R.id.fragment4Fragment)
            .openIn(navController)
    }

    fun from6To7Screen() {
        screen(R.id.action_fragment6Fragment_to_fragment7Fragment)
            .toFeatureNavDirection()
            .replaceAllTo(R.id.fragment3Fragment, inclusive = false)
            .openIn(navController)
    }

    fun backTo2Screen() {
        backTo(R.id.fragment2Fragment)
    }

    fun backTo4Screen() {
        backTo(R.id.fragment4Fragment)
    }

    fun backTo6Screen() {
        backTo(R.id.fragment6Fragment)
    }
}