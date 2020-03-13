package com.mvp_kotlin_login_register_retrofit_example.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
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
import com.mvp_kotlin_login_register_retrofit_example.presenter.implemenation.AccountPresenterImpl
import com.mvp_kotlin_login_register_retrofit_example.presenter.implemenation.ComplientPresenterImpl
import com.mvp_kotlin_login_register_retrofit_example.presenter.implemenation.HistoryPresenterImpl
import com.mvp_kotlin_login_register_retrofit_example.presenter.implemenation.ResponseHistory
import com.mvp_kotlin_login_register_retrofit_example.presenter.interfaces.IComplaintPresenter
import com.mvp_kotlin_login_register_retrofit_example.presenter.interfaces.IHistoryPresenter
import com.mvp_kotlin_login_register_retrofit_example.view.interfaces.IAccountView
import com.mvp_kotlin_login_register_retrofit_example.view.interfaces.IHistoryView
import kotlinx.android.synthetic.main.fragment_list.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class HistoryFragment : Fragment(), IHistoryView {
    override fun onHistoryResult(result: Boolean, code: Int, response: ResponseHistory) {

    }

    override fun onSetProgressBarVisibility(visibility: Int) {
    }

    private lateinit var histPresenter: IHistoryPresenter
    private val mPrefs: SharedPreferences by lazy { PreferenceManager.getDefaultSharedPreferences(context) }


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

//        setupView()
        rv_history.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv_history.adapter = Adapter(context!!, posts)
    }

    private fun setupView() {
        histPresenter = HistoryPresenterImpl(context as IHistoryView , context!!)
        histPresenter.setProgressBarVisibility(View.INVISIBLE)
        histPresenter.getHistory(mPrefs.getInt("id", 0))

    }
}