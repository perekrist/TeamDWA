package com.mvp_kotlin_login_register_retrofit_example.model.responseModels

data class UserResponseModel(
    var first_name: String,
    var last_name: String,
    var patronymic: String,
    var email: String,
    var phone: String
)