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


//TESTING
package com.bluecat.githubfeed.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bluecat.githubfeed.R
import com.bluecat.githubfeed.model.TestData
import kotlinx.android.synthetic.main.fragment_feed.*
import com.bluecat.githubfeed.ui.adapters.FeedAdapter
import com.bluecat.githubfeed.ui.viewHolders.FeedViewHolder

import org.jetbrains.anko.support.v4.toast

class FeedFragment : Fragment(), FeedViewHolder.Delegate {

    private val adapter by lazy { FeedAdapter(this) }
    private var count = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_feed, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeUI()
    }

    private fun initializeUI() {
        adding.setOnClickListener {
            toast("add")
            addItems(TestData(count, etvt.text.toString()))
            etvt.text.clear()
        }

        main_recyclerview.adapter = adapter
        main_recyclerview.layoutManager = LinearLayoutManager(context)
    }

    private fun addItems(str: TestData) {
        adapter.addItems(str)
    }

    override fun onItemClick(adapterPosition: Int, sampleItem: TestData) {
        count--
        adapter.removeItem(adapterPosition)
    }

}