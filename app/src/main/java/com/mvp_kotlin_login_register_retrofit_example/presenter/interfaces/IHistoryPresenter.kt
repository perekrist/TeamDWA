package com.mvp_kotlin_login_register_retrofit_example.presenter.interfaces

interface IHistoryPresenter {
    fun getHistory(
        id: Int
    )
    fun setProgressBarVisibility(visibility: Int)
}