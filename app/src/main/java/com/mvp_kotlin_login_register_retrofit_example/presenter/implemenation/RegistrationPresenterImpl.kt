package com.mvp_kotlin_login_register_retrofit_example.presenter.implemenation

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.util.Log
import com.mvp_kotlin_login_register_retrofit_example.model.responseModels.LoginResponseModel
import com.mvp_kotlin_login_register_retrofit_example.webServices.Webservice
import com.mvp_kotlin_login_register_retrofit_example.presenter.interfaces.IRegistrationPresenter
import com.mvp_kotlin_login_register_retrofit_example.view.interfaces.IRegistrationView
import com.google.gson.Gson
import okhttp3.RequestBody
import org.json.JSONObject

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistrationPresenterImpl(private var iRegistrationView: IRegistrationView , val context: Context) : IRegistrationPresenter {
    lateinit var registerationResponseModel: LoginResponseModel
    private val mPrefs: SharedPreferences by lazy { PreferenceManager.getDefaultSharedPreferences(context) }

    override fun doRegistration(
        firstName: String,
        lastName: String,
        patronymic: String,
        email: String,
        password: String,
        mobile: String
    ) {
        val data = HashMap<String, String>()
        data["first_name"] = firstName
        data["last_name"] = lastName
        data["patronymic"] = patronymic
        data["phone"] = mobile
        data["email"] = email
        data["password"] = password

        val body = RequestBody.create(
            okhttp3.MediaType.parse("application/json"),
            JSONObject(data).toString()
        )

        Webservice.getInstance().getApi().register(body).enqueue(object : Callback<LoginResponseModel> {
            override fun onResponse(call: Call<LoginResponseModel>, response: Response<LoginResponseModel>) {
                if (!response.isSuccessful) {
                    iRegistrationView.onRegistrationResult(false, -1, "")
                } else {
                    registerationResponseModel = response.body()!!

                    val prefsEditor = mPrefs.edit()
                    val gson = Gson()
                    val json = gson.toJson(registerationResponseModel)
                    prefsEditor.putString("MyObject", json)
                    prefsEditor.putBoolean("isLogin", true)
                    prefsEditor.apply()

                    iRegistrationView.onRegistrationResult(true, 1, "")
                }
            }

            override fun onFailure(call: Call<LoginResponseModel>, t: Throwable) {
                Log.e("login", "onFailure: ", t)
                iRegistrationView.onRegistrationResult(false, 0 , "")
            }
        })
    }

    override fun setProgressBarVisibility(visibility: Int) {
        iRegistrationView.onSetProgressBarVisibility(visibility)
    }
}