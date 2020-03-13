package com.mvp_kotlin_login_register_retrofit_example.presenter.interfaces

interface IComplaintPresenter {
    fun sendComplaint(
        id: Int,
        date: String,
        lat: Float,
        lng: Float,
        comment: String,
        plate: String
    )
    fun setProgressBarVisibility(visibility: Int)
}