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
import com.bluecat.githubfeed.ui.adapters.FeedAdapter
import com.bluecat.githubfeed.ui.viewHolders.FeedViewHolder
import kotlinx.android.synthetic.main.fragment_feed.view.*
import org.jetbrains.anko.support.v4.toast

class FeedFragment : Fragment(), FeedViewHolder.Delegate {

    private var rootView: View? = null
    private val adapter by lazy { FeedAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_feed, container, false)

        initializeUI()

        return rootView
    }

    private fun initializeUI() = rootView?.also {

        it.main_recyclerview.adapter = adapter
        it.main_recyclerview.layoutManager = LinearLayoutManager(context)

        for (r in 0..22) {
            addItems(TestData("${r}:TESTDATA"))
        }
        // TODO
    }

    private fun addItems(str:TestData)
    {
        adapter.addItems(str)
    }

    override fun onItemClick(sampleItem: TestData) {
        toast(sampleItem.testData)
    }

}