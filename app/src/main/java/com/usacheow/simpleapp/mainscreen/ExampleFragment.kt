package com.usacheow.simpleapp.mainscreen

import android.os.Bundle
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import com.usacheow.authorization.presentation.fragment.AuthContainerFragment
import com.usacheow.authorization.presentation.fragment.PinCodeFragment
import com.usacheow.authorization.presentation.fragment.SignInFragment
import com.usacheow.authorization.presentation.fragment.SignUpFragment
import com.usacheow.coreuikit.demo.CalendarFragment
import com.usacheow.coreuikit.demo.FontsFragment
import com.usacheow.coreuikit.demo.ViewsFragment
import com.usacheow.coreuikit.demo.WidgetsFragment
import com.usacheow.coreuikit.fragments.SimpleFragment
import com.usacheow.coreuikit.utils.ext.doOnApplyWindowInsets
import com.usacheow.coreuikit.utils.ext.doOnClick
import com.usacheow.coreuikit.utils.ext.toPx
import com.usacheow.diprovider.DiProvider
import com.usacheow.simpleapp.R
import kotlinx.android.synthetic.main.fragment_example.calendarScreen
import kotlinx.android.synthetic.main.fragment_example.exampleHeaderView
import kotlinx.android.synthetic.main.fragment_example.exampleRootView
import kotlinx.android.synthetic.main.fragment_example.fontsScreen
import kotlinx.android.synthetic.main.fragment_example.pinCodeScreen
import kotlinx.android.synthetic.main.fragment_example.signInByPhoneScreen
import kotlinx.android.synthetic.main.fragment_example.signInScreen
import kotlinx.android.synthetic.main.fragment_example.signUpScreen
import kotlinx.android.synthetic.main.fragment_example.viewsScreen
import kotlinx.android.synthetic.main.fragment_example.widgetsScreen

class ExampleFragment : SimpleFragment() {

    override val layoutId = R.layout.fragment_example

    companion object {
        fun newInstance() = ExampleFragment()
    }

    override fun inject(diProvider: DiProvider) {
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        exampleRootView.doOnApplyWindowInsets { insets, _ ->
            exampleRootView.updatePadding(bottom = insets.systemWindowInsetBottom)
            exampleHeaderView.updatePadding(top = insets.systemWindowInsetTop + 16.toPx)
        }
        fontsScreen.doOnClick { show(FontsFragment.newInstance()) }
        widgetsScreen.doOnClick { show(WidgetsFragment.newInstance()) }
        viewsScreen.doOnClick { show(ViewsFragment.newInstance()) }
        pinCodeScreen.doOnClick { show(PinCodeFragment.newInstance()) }
        signInByPhoneScreen.doOnClick { show(AuthContainerFragment.newInstance()) }
        signInScreen.doOnClick { show(SignInFragment.newInstance()) }
        signUpScreen.doOnClick { show(SignUpFragment.newInstance()) }
        calendarScreen.doOnClick { show(CalendarFragment.newInstance()) }
    }

    private fun show(fragment: Fragment) {
        getContainer { show(fragment) }
    }
}