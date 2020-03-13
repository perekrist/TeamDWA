package com.mvp_kotlin_login_register_retrofit_example.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.mvp_kotlin_login_register_retrofit_example.R
import com.mvp_kotlin_login_register_retrofit_example.classes.Post
import com.mvp_kotlin_login_register_retrofit_example.classes.posts
import kotlinx.android.synthetic.main.fragment_list.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class HistoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_list, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_history.layoutManager = LinearLayoutManager(this.activity, LinearLayoutManager.VERTICAL, false)
        rv_history.adapter = Adapter(this.context!!, posts)
    }
}