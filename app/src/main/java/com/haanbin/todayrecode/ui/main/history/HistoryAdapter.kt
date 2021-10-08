package com.haanbin.todayrecode.ui.main.history

import androidx.recyclerview.widget.DiffUtil
import com.haanbin.todayrecode.R
import com.haanbin.todayrecode.base.BaseListAdapter
import com.haanbin.todayrecode.data.entity.RecodeItem

class HistoryAdapter(viewModel: HistoryViewModel) : BaseListAdapter<RecodeItem>(DIFF_CALLBACK) {

    init {
        this.listener = viewModel
    }

    override fun setLayoutViewType(): Int = R.layout.item_history

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RecodeItem>() {
            override fun areItemsTheSame(oldItem: RecodeItem, newItem: RecodeItem): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: RecodeItem, newItem: RecodeItem): Boolean =
                oldItem == newItem
        }
    }
}