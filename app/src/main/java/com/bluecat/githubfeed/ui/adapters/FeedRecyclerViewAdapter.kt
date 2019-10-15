/*
 * Copyright 2019 Team Mulro in BlueCat-Community
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bluecat.githubfeed.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bluecat.githubfeed.R
import com.bluecat.githubfeed.model.TestData
import kotlinx.android.synthetic.main.item_feed.view.*

//TESTING
class FeedRecyclerViewAdapter(private var items: ArrayList<TestData>) :
    RecyclerView.Adapter<FeedRecyclerViewAdapter.FeedViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FeedViewHolder(parent)
    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        with(holder) {
            tb.text = items[position].testData
            //TODO
        }
    }

    inner class FeedViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_feed, parent, false
        )
    ) {
       val tb: TextView =itemView.testingitem
        //TODO
    }
}