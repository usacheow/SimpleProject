package com.kapmayn.core.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.kapmayn.core.presentation.base.IContainer
import com.kapmayn.core.utils.inTransaction
import java.io.Serializable

private const val FIRST_STACK_NUMBER = 0

class BottomBarHistoryManager(
    private val fragmentManager: FragmentManager,
    private val transactionContainerId: Int,
    private vararg val initFragmentsAction: () -> Fragment
) {

    private val sections = MutableList(initFragmentsAction.size) { "" }
    private var activeSectionNumber = 0
    private var listener: OnSelectedSectionChangedListener? = null

    fun getState() = State(sections, activeSectionNumber)

    fun setState(state: BottomBarHistoryManager.State) {
        sections.clear()
        sections += state.sections
        openSection(state.activeSectionNumber)
    }

    fun setListener(listener: OnSelectedSectionChangedListener) {
        this.listener = listener
    }

    fun openSection(sectionNumber: Int) {
        if (sections[sectionNumber].isEmpty()) {
            val nextFragment = initFragmentsAction[sectionNumber].invoke()
            fragmentManager.addFragment(nextFragment)
            sections[sectionNumber] = nextFragment.tag()
        } else {
            val nextFragmentTag = sections[sectionNumber]
            fragmentManager.showFragment(nextFragmentTag)
        }
        activeSectionNumber = sectionNumber
    }

    fun resetSection() {
        fragmentManager.inTransaction {
            val activeFragmentTag = sections[activeSectionNumber]
            val activeFragment = fragmentManager.findFragmentByTag(activeFragmentTag)
            if (activeFragment is IContainer) {
                activeFragment.reset()
            }
            this
        }
    }

    fun backSection() {
        val activeFragmentTag = sections[activeSectionNumber]
        val activeFragment = fragmentManager.findFragmentByTag(activeFragmentTag)
        val isBackProcesses = if (activeFragment is IContainer) {
            activeFragment.onBackClicked()
        } else {
            false
        }

        if (!isBackProcesses) {
            when (activeSectionNumber) {
                FIRST_STACK_NUMBER -> listener?.closeScreen()
                else -> listener?.selectSection(FIRST_STACK_NUMBER)
            }
        }
    }

    private fun FragmentManager.addFragment(nextFragment: Fragment) {
        val activeFragmentTag = sections[activeSectionNumber]
        val activeFragment = if (activeFragmentTag.isNotEmpty()) {
            findFragmentByTag(activeFragmentTag)
        } else {
            null
        }

        inTransaction {
            activeFragment?.let { hide(it) }
            add(transactionContainerId, nextFragment, nextFragment.tag())
            this
        }
    }

    private fun FragmentManager.showFragment(nextFragmentTag: String) {
        val activeFragmentTag = sections[activeSectionNumber]
        val activeFragment = findFragmentByTag(activeFragmentTag)
        val nextFragment = findFragmentByTag(nextFragmentTag)

        inTransaction {
            activeFragment?.let { hide(it) }
            nextFragment?.let { show(it) }
            this
        }
    }

    private fun Fragment.tag() = "${this.hashCode()}"

    data class State(
        val sections: List<String>,
        var activeSectionNumber: Int
    ) : Serializable

    interface OnSelectedSectionChangedListener {

        fun selectSection(sectionNumber: Int)

        fun closeScreen()
    }
}