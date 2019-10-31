package com.bluecat.githubfeed.ui.adapters

import android.view.View
import com.bluecat.githubfeed.R
import com.bluecat.githubfeed.model.TestData
import com.bluecat.githubfeed.ui.viewHolders.FeedViewHolder
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.SectionRow

class FeedAdapter(private val delegate: FeedViewHolder.Delegate) : BaseAdapter() {
    private val sectionItem = 0

    init {
        addSection(ArrayList<TestData>())
    }

    fun addItems(sampleItem: TestData) {
        addItemOnSection(sectionItem, sampleItem)
        notifyDataSetChanged()
    }

    fun removeItem(index: Int) {
        sections()[0].removeAt(index)
        notifyDataSetChanged()
    }

    override fun layout(sectionRow: SectionRow) = R.layout.item_feed
    override fun viewHolder(layout: Int, view: View) = FeedViewHolder(view, delegate)
}