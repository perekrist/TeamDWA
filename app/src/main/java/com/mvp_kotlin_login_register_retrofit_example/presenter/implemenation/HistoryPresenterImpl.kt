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
import com.mvp_kotlin_login_register_retrofit_example.classes.Post
import com.mvp_kotlin_login_register_retrofit_example.classes.posts
import com.mvp_kotlin_login_register_retrofit_example.presenter.interfaces.IComplaintPresenter
import com.mvp_kotlin_login_register_retrofit_example.presenter.interfaces.IHistoryPresenter
import com.mvp_kotlin_login_register_retrofit_example.view.RegistrationActivity
import com.mvp_kotlin_login_register_retrofit_example.view.interfaces.IComplaintView
import com.mvp_kotlin_login_register_retrofit_example.view.interfaces.IHistoryView
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryPresenterImpl(var iHistoryView: IHistoryView, val context: Context) : IHistoryPresenter {

    lateinit var loginResponseModel: LoginResponseModel
    private val mPrefs: SharedPreferences by lazy { PreferenceManager.getDefaultSharedPreferences(context) }


    override fun getHistory(id: Int) {
        val data = HashMap<String, String>()
        data["id"] = id.toString()

        val body = RequestBody.create(
            okhttp3.MediaType.parse("application/json"),
            JSONObject(data).toString()
        )


        Webservice.getInstance().getApi().complaint(body).enqueue(object : Callback<LoginResponseModel> {
            override fun onResponse(call: Call<LoginResponseModel>, response: Response<LoginResponseModel>) =
                if (!response.isSuccessful) {
                    iHistoryView.onHistoryResult(false, -1, ResponseHistory(""))
                } else {
                    loginResponseModel = response.body()!!

                    val prefsEditor = mPrefs.edit()
                    val gson = Gson()
                    val json = gson.toJson(loginResponseModel)
                    println(ResponseHistory(json))

//                    prefsEditor.putInt("id", id)
//                    prefsEditor.putBoolean("isLogin" , true)
//                    prefsEditor.apply()

                    iHistoryView.onHistoryResult(true, 1, ResponseHistory(json))
                }

            override fun onFailure(call: Call<LoginResponseModel>, t: Throwable) {
                Log.e("complaint", "onFailure: ", t)
                iHistoryView.onHistoryResult(false, 0, ResponseHistory(""))
            }
        })
    }


    override fun setProgressBarVisibility(visibility: Int) {
        iHistoryView.onSetProgressBarVisibility(visibility)
    }
}

class ResponseHistory(json: String) : JSONObject(json) {
    val data = this.optJSONArray("data")
        ?.let { 0.until(it.length()).map { i -> it.optJSONObject(i) } }
        ?.map { Foo(it.toString()) } // transforms each JSONObject of the array into Foo
}

class Foo(json: String) : JSONObject(json) {
    val id = this.optInt("id")
    val number: String = this.optString("number")
    val desc: String = this.optString("comment")

    init {
        posts.add(Post(
            posts.size + 1,
            number,
            desc
        ))
    }
}
