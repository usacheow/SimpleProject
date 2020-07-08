package com.usacheow.coreuikit.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.usacheow.coreuikit.base.IBackListener
import com.usacheow.coreuikit.base.IContainer
import com.usacheow.coreuikit.utils.ext.hashTag
import com.usacheow.coreuikit.utils.ext.inTransaction
import java.io.Serializable

private const val FIRST_STACK_NUMBER = 0

class MultiStackHistoryManager(
    private val fragmentManager: FragmentManager,
    private val transactionContainerId: Int,
    private vararg val initFragmentsAction: () -> Fragment
) {

    var listener: OnSectionChangedListener? = null

    private var sections = MutableList(initFragmentsAction.size) { "" }
    private var activeSectionNumber = FIRST_STACK_NUMBER

    fun getState() = State(sections, activeSectionNumber)

    fun setState(state: State) {
        sections = state.sections.toMutableList()
        if (sections.isEmpty()) {
            sections = MutableList(initFragmentsAction.size) { "" }
        }
        activeSectionNumber = state.activeSectionNumber
    }

    fun openActiveSection() {
        openSection(activeSectionNumber)
        listener?.onSectionChanged(activeSectionNumber)
    }

    fun openSection(sectionNumber: Int) {
        if (sections.size <= sectionNumber) {
            return
        }
        if (sections[sectionNumber].isEmpty()) {
            val nextFragment = initFragmentsAction[sectionNumber].invoke()
            fragmentManager.addFragment(nextFragment)
            sections[sectionNumber] = nextFragment.hashTag()
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

    fun backSection(): Boolean {
        val activeFragmentTag = sections[activeSectionNumber]
        val activeFragment = fragmentManager.findFragmentByTag(activeFragmentTag)
        val isBackProcesses = if (activeFragment is IBackListener) {
            activeFragment.onBackPressed()
        } else {
            false
        }

        return when {
            isBackProcesses -> true
            activeSectionNumber != FIRST_STACK_NUMBER -> {
                openSection(FIRST_STACK_NUMBER)
                listener?.onSectionChanged(FIRST_STACK_NUMBER)
                true
            }
            else -> false
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
            add(transactionContainerId, nextFragment, nextFragment.hashTag())
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

    data class State(
        val sections: List<String>,
        val activeSectionNumber: Int
    ) : Serializable

    interface OnSectionChangedListener {

        fun onSectionChanged(sectionNumber: Int)
    }
}