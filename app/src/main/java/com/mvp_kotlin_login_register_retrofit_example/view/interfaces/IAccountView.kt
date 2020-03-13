package com.mvp_kotlin_login_register_retrofit_example.view.interfaces

import com.mvp_kotlin_login_register_retrofit_example.presenter.implemenation.ResponseAcc

interface IAccountView {
    fun onAccountResult(result: Boolean, code: Int, response: ResponseAcc)
    fun onSetProgressBarVisibility(visibility: Int)
}