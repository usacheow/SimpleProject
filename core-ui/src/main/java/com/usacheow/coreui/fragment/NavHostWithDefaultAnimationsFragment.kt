package com.usacheow.coreui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navOptions
import com.usacheow.coreui.R

private val defaultNavOptions = navOptions {
    anim {
        enter = R.anim.anim_enter_from_right
        exit = R.anim.anim_exit_to_left
        popEnter = R.anim.anim_enter_from_left
        popExit = R.anim.anim_exit_to_right
//        enter = R.animator.nav_default_enter_anim
//        exit = R.animator.nav_default_exit_anim
//        popEnter = R.animator.nav_default_pop_enter_anim
//        popExit = R.animator.nav_default_pop_exit_anim
    }
}

private val emptyNavOptions = navOptions {}

class NavHostWithDefaultAnimationsFragment : NavHostFragment() {

    override fun onCreateNavHostController(navHostController: NavHostController) {
        super.onCreateNavHostController(navHostController)
        navController.navigatorProvider.addNavigator(
            FragmentNavigatorWithDefaultAnimations(requireContext(), childFragmentManager, id)
        )
    }
}

@Navigator.Name("fragment")
class FragmentNavigatorWithDefaultAnimations(
    context: Context,
    manager: FragmentManager,
    containerId: Int,
) : FragmentNavigator(context, manager, containerId) {

    override fun navigate(
        destination: Destination,
        args: Bundle?,
        navOptions: NavOptions?,
        navigatorExtras: Navigator.Extras?,
    ): NavDestination? {
        val shouldUseTransitionsInstead = navigatorExtras != null
        val copiedNavOptions = when {
            shouldUseTransitionsInstead -> navOptions
            else -> navOptions?.copyNavOptionsWithDefaultAnimations() ?: defaultNavOptions
        }

        return super.navigate(destination, args, copiedNavOptions, navigatorExtras)
    }

    private fun NavOptions.copyNavOptionsWithDefaultAnimations(): NavOptions {
        val originalNavOptions = this

        return navOptions {
            launchSingleTop = originalNavOptions.shouldLaunchSingleTop()
            popUpTo(originalNavOptions.popUpToId) {
                inclusive = originalNavOptions.isPopUpToInclusive()
            }
            anim {
                enter = when (originalNavOptions.enterAnim) {
                    emptyNavOptions.enterAnim -> defaultNavOptions.enterAnim
                    else -> originalNavOptions.enterAnim
                }
                exit = when (originalNavOptions.exitAnim) {
                    emptyNavOptions.exitAnim -> defaultNavOptions.exitAnim
                    else -> originalNavOptions.exitAnim
                }
                popEnter = when (originalNavOptions.popEnterAnim) {
                    emptyNavOptions.popEnterAnim -> defaultNavOptions.popEnterAnim
                    else -> originalNavOptions.popEnterAnim
                }
                popExit = when (originalNavOptions.popExitAnim) {
                    emptyNavOptions.popExitAnim -> defaultNavOptions.popExitAnim
                    else -> originalNavOptions.popExitAnim
                }
            }
        }
    }
}