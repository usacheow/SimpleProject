package com.kapmayn.coreuikit.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.kapmayn.coreuikit.R

open class StringSpinnerAdapter(
    private val layoutResId: Int = R.layout.view_string_spinner_item,
    private val models: MutableList<String> = mutableListOf()
) : BaseAdapter() {

    override fun getCount() = models.size

    override fun isEmpty() = models.isEmpty()

    override fun getItem(index: Int) = models[index]

    override fun getItemId(index: Int) = index.toLong()

    override fun getView(index: Int, view: View?, viewGroup: ViewGroup) = processView(index, view, viewGroup)

    override fun getDropDownView(index: Int, view: View?, viewGroup: ViewGroup) = processView(index, view, viewGroup)

    fun updateData(items: List<String>) {
        models.clear()
        models += items.toMutableList()
        notifyDataSetChanged()
    }

    protected open fun processView(index: Int, originalView: View?, viewGroup: ViewGroup): View {
        val item = models[index]
        return originalView
            ?: LayoutInflater.from(viewGroup.context).inflate(layoutResId, viewGroup, false).apply {
                findViewById<TextView>(R.id.spinnerTitle).text = models[index]
            }
    }
}