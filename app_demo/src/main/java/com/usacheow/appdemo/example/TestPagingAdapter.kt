package com.usacheow.appdemo.example

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.usacheow.coreui.adapter.ViewTypesAdapter
import com.usacheow.coreui.adapter.base.ViewType

class TestPagingAdapter : PagingDataAdapter<TestModel, ViewTypesAdapter.ViewTypesViewHolder>(TestModelDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewTypesAdapter.ViewTypesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return ViewTypesAdapter.ViewTypesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewTypesAdapter.ViewTypesViewHolder, position: Int) {
        getItem(position)?.let(holder::populate)
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position)?.layoutId ?: 0
    }

    object TestModelDiffUtil : DiffUtil.ItemCallback<TestModel>() {

        override fun areItemsTheSame(oldItem: TestModel, newItem: TestModel): Boolean {
            return oldItem.layoutId == newItem.layoutId
        }

        override fun areContentsTheSame(oldItem: TestModel, newItem: TestModel): Boolean {
            return oldItem.name == newItem.name &&
                oldItem.surname == newItem.surname
        }
    }
}

data class TestModel(
    val name: String,
    val surname: String,
) : ViewType(0)
