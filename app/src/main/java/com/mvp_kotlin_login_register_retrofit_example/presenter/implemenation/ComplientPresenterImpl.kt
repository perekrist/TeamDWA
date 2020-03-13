package com.mvp_kotlin_login_register_retrofit_example.presenter.implemenation


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.util.Log
import com.mvp_kotlin_login_register_retrofit_example.webServices.Webservice
import com.mvp_kotlin_login_register_retrofit_example.model.responseModels.LoginResponseModel
import com.mvp_kotlin_login_register_retrofit_example.presenter.interfaces.ILoginPresenter
import com.mvp_kotlin_login_register_retrofit_example.view.interfaces.ILoginView
import com.google.gson.Gson
import com.mvp_kotlin_login_register_retrofit_example.presenter.interfaces.IComplaintPresenter
import com.mvp_kotlin_login_register_retrofit_example.view.RegistrationActivity
import com.mvp_kotlin_login_register_retrofit_example.view.interfaces.IComplaintView
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ComplientPresenterImpl(var iComplaintView: IComplaintView , val context: Context) : IComplaintPresenter {

    lateinit var loginResponseModel: LoginResponseModel
    private val mPrefs: SharedPreferences by lazy { PreferenceManager.getDefaultSharedPreferences(context) }

    override fun sendComplaint(
        id: Int,
        date: String,
        lat: Float,
        lng: Float,
        comment: String,
        plate: String
    ) {
        val data = HashMap<String, String>()
        data["id"] = id.toString()
        data["date"] = date
        data["lat"] = lat.toString()
        data["lng"] = lng.toString()
        data["comment"] = comment
        data["number"] = plate


        val body = RequestBody.create(
            okhttp3.MediaType.parse("application/json"),
            JSONObject(data).toString()
        )


        Webservice.getInstance().getApi().complaint(body).enqueue(object : Callback<LoginResponseModel> {
            override fun onResponse(call: Call<LoginResponseModel>, response: Response<LoginResponseModel>) =
                if (!response.isSuccessful) {
                    iComplaintView.onComplaintResult(false, -1)
                } else {
                    loginResponseModel = response.body()!!

                    val prefsEditor = mPrefs.edit()
                    val gson = Gson()
                    val json = gson.toJson(loginResponseModel)

                    val id = Response(json).user_id!!.toInt()

                    prefsEditor.putInt("id", id)
                    prefsEditor.putBoolean("isLogin" , true)
                    prefsEditor.apply()

                    iComplaintView.onComplaintResult(true, 1)
                }

            override fun onFailure(call: Call<LoginResponseModel>, t: Throwable) {
                Log.e("complaint", "onFailure: ", t)
                iComplaintView.onComplaintResult(false, 0)
            }
        })
    }

    override fun setProgressBarVisibility(visibility: Int) {
        iComplaintView.onSetProgressBarVisibility(visibility)
    }
}
