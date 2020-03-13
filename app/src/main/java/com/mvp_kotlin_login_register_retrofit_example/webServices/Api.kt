package com.mvp_kotlin_login_register_retrofit_example.webServices

import com.mvp_kotlin_login_register_retrofit_example.model.responseModels.LoginResponseModel
import com.mvp_kotlin_login_register_retrofit_example.model.responseModels.UserResponseModel
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface Api {

    @POST("login")
    fun login(@Body headers: RequestBody): Call<LoginResponseModel>

    @POST("registration")
    fun register(@Body headers: RequestBody): Call<LoginResponseModel>

    @POST("show_person")
    fun account(@Body headers: RequestBody): Call<UserResponseModel>

    @POST("send_complaint")
    fun complaint(@Body headers: RequestBody): Call<LoginResponseModel>

}