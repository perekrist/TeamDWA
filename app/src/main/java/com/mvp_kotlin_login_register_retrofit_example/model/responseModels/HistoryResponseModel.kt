package com.mvp_kotlin_login_register_retrofit_example.model.responseModels

import com.mvp_kotlin_login_register_retrofit_example.classes.Post

data class HistoryResponseModel(
    var complaints: ArrayList<Post>
)