package com.mvp_kotlin_login_register_retrofit_example.presenter.interfaces

interface ILoginPresenter {
    fun doLogin(email: String, passwd: String)
    fun setProgressBarVisibility(visibility: Int)
}