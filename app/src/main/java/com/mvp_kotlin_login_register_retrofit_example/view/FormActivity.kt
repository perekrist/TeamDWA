package com.mvp_kotlin_login_register_retrofit_example.view

import android.content.Intent
import android.content.SharedPreferences
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
//import com.google.android.gms.location.FusedLocationProviderClient
//import com.google.android.gms.location.LocationCallback
//import com.google.android.gms.location.LocationRequest
//import com.google.android.gms.location.LocationServices
import com.mvp_kotlin_login_register_retrofit_example.R
import com.mvp_kotlin_login_register_retrofit_example.classes.*
import com.mvp_kotlin_login_register_retrofit_example.presenter.implemenation.ComplientPresenterImpl
import com.mvp_kotlin_login_register_retrofit_example.presenter.implemenation.LoginPresenterImpl
import com.mvp_kotlin_login_register_retrofit_example.presenter.interfaces.IComplaintPresenter
import com.mvp_kotlin_login_register_retrofit_example.presenter.interfaces.ILoginPresenter
import com.mvp_kotlin_login_register_retrofit_example.view.interfaces.IComplaintView
import kotlinx.android.synthetic.main.activity_form.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.content_form.*
import kotlinx.android.synthetic.main.fragment_account.*
import java.text.SimpleDateFormat
import java.util.*

class FormActivity : AppCompatActivity(), IComplaintView {
    private lateinit var complPresenter: IComplaintPresenter

    override fun onComplaintResult(result: Boolean, code: Int) {
        if (result && code == 1) {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this,  getString(R.string.please_try_again), Toast.LENGTH_SHORT).show()

        }
    }

    override fun onSetProgressBarVisibility(visibility: Int) {
    }

    private val mPrefs: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(
            this
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)
        buttonSubmitForm.setOnClickListener {
            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val currentDate = sdf.format(Date())

            complPresenter.sendComplaint(
                mPrefs.getInt("id", 0),
                currentDate,
                37f,
                22f,
                editFormDescription.text.toString(),
                number.text.toString()
                )

            posts.add(Post(
                posts.size + 1,
                number.text.toString(),
                editFormDescription.text.toString()

            ))
        }

        complPresenter = ComplientPresenterImpl(this, this)


        buttonCancel.setOnClickListener {
            finish()
        }

        user_name.text = currenPerson.nameReal
        last_name.text = currenPerson.surnname
        pat.text = currenPerson.parentific
        emai.text = currenPerson.email
        ph.text = currenPerson.phone


    }
}
