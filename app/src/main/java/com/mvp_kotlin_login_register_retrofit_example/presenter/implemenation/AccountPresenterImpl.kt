package com.mvp_kotlin_login_register_retrofit_example.presenter.implemenation


import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.util.Log
import com.mvp_kotlin_login_register_retrofit_example.webServices.Webservice
import com.mvp_kotlin_login_register_retrofit_example.model.responseModels.LoginResponseModel
import com.google.gson.Gson
import com.mvp_kotlin_login_register_retrofit_example.model.responseModels.UserResponseModel
import com.mvp_kotlin_login_register_retrofit_example.presenter.interfaces.IAccountPresenter
import com.mvp_kotlin_login_register_retrofit_example.view.interfaces.IAccountView
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountPresenterImpl(var iAccountView: IAccountView, val context: Context) : IAccountPresenter {

    lateinit var userResponseModel: UserResponseModel
    private val mPrefs: SharedPreferences by lazy { PreferenceManager.getDefaultSharedPreferences(context) }

    override fun getAccountData(id: Int) {
        println("get account data")

        val data = HashMap<String, String>()
        data["id"] = id.toString()

        val body = RequestBody.create(
            okhttp3.MediaType.parse("application/json"),
            JSONObject(data).toString()
        )


        Webservice.getInstance().getApi().account(body).enqueue(object : Callback<UserResponseModel> {
            override fun onResponse(call: Call<UserResponseModel>, response: Response<UserResponseModel>) {
                if (!response.isSuccessful) {
                    iAccountView.onAccountResult(false, -1, ResponseAcc(""))
                } else {
                    userResponseModel = response.body()!!

                    val gson = Gson()
                    val json = gson.toJson(userResponseModel)

                    iAccountView.onAccountResult(true, 1, ResponseAcc(json))
                }
            }

            override fun onFailure(call: Call<UserResponseModel>, t: Throwable) {
                Log.e("login", "onFailure: ", t)
                iAccountView.onAccountResult(false, 0, ResponseAcc(""))
            }
        })
    }

    override fun setProgressBarVisibility(visibility: Int) {
        iAccountView.onSetProgressBarVisibility(visibility)
    }
}

class ResponseAcc(json: String) : JSONObject(json) {
    val user_email: String? = this.optString("email")
    val user_first_name: String? = this.optString("first_name")
    val user_last_name: String? = this.optString("last_name")
    val user_patronymic: String? = this.optString("patronymic")
    val user_phone: String? = this.optString("phone")

}
