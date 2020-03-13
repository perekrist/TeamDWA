package com.mvp_kotlin_login_register_retrofit_example.view.interfaces

import com.mvp_kotlin_login_register_retrofit_example.presenter.implemenation.ResponseHistory

interface IHistoryView {
    fun onHistoryResult(result: Boolean, code: Int, response: ResponseHistory)
    fun onSetProgressBarVisibility(visibility: Int)
}