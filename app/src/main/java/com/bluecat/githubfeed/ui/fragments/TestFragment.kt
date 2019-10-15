package com.bluecat.githubfeed.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bluecat.githubfeed.R
import kotlinx.android.synthetic.main.fragment_test.view.*

class TestFragment: Fragment() {

    private lateinit var mView:View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // TEST
        mView = inflater.inflate(R.layout.fragment_test, container,false).apply {
            this.testbox.text = "It's Test Fragment"
        }

        return mView
    }
}