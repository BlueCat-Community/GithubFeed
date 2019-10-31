package com.bluecat.githubfeed.ui.viewHolders

import android.view.View
import com.bluecat.githubfeed.model.TestData
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import kotlinx.android.synthetic.main.item_feed.view.*

class FeedViewHolder(view: View, private val delegate: Delegate) : BaseViewHolder(view) {
    private lateinit var sampleItem: TestData

    interface Delegate {
        fun onItemClick(adapterPosition: Int, sampleItem: TestData)
    }

    override fun bindData(data: Any) {
        if (data is TestData) {
            sampleItem = data
            drawItem()
        }
    }

    private fun drawItem() {
        itemView.run {
            testingitem.text = sampleItem.testData
        }
    }

    override fun onClick(v: View?) {
        delegate.onItemClick(adapterPosition, this.sampleItem)
    }

    override fun onLongClick(v: View?) = false
}