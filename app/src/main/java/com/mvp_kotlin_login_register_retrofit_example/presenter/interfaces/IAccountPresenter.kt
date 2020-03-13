package com.mvp_kotlin_login_register_retrofit_example.presenter.interfaces

interface IAccountPresenter {
    fun getAccountData(id: Int)
    fun setProgressBarVisibility(visibility: Int)
}