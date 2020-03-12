package com.mvp_kotlin_login_register_retrofit_example.presenter.interfaces

interface IRegistrationPresenter {
    fun doRegistration(firstName: String, lastName: String, patronymic: String, email: String, password: String, mobile: String)
    fun setProgressBarVisibility(visibility: Int)
}