package com.mvp_kotlin_login_register_retrofit_example.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.mvp_kotlin_login_register_retrofit_example.R
import com.mvp_kotlin_login_register_retrofit_example.presenter.interfaces.IRegistrationPresenter
import com.mvp_kotlin_login_register_retrofit_example.presenter.implemenation.RegistrationPresenterImpl
import com.mvp_kotlin_login_register_retrofit_example.view.interfaces.IRegistrationView
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.android.synthetic.main.activity_registration.input_mobile
import kotlinx.android.synthetic.main.activity_registration.input_password
import java.util.regex.Pattern


class RegistrationActivity : AppCompatActivity()  , IRegistrationView {

    private lateinit var registrationPresenter: IRegistrationPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        setupView()
    }

    private fun setupView() {
        btn_register.setOnClickListener {
            registrationPresenter.setProgressBarVisibility(View.VISIBLE)
            btn_register.isEnabled = false
            val firstName = input_first_name.text.toString().trim()
            val lastName = input_last_name.text.toString().trim()
            val patronymic = input_patronymic.text.toString().trim()
            val phoneNumber = input_mobile.text.toString().trim()
            val email = input_email.text.toString().trim()
            val passwd = input_password.text.toString().trim()


            if (areFieldsValid(firstName , lastName, patronymic, phoneNumber,  email, passwd))
                registrationPresenter.doRegistration(firstName , lastName , patronymic, email , passwd , phoneNumber)
        }

        //init
        registrationPresenter = RegistrationPresenterImpl(this , this)
        registrationPresenter.setProgressBarVisibility(View.INVISIBLE)

    }

    private fun areFieldsValid(
        firstName: String,
        lastName: String,
        patronymic: String,
        phoneNumber: String,
        email: String,
        passwd: String
    ): Boolean {

        if (firstName.isEmpty() || lastName.isEmpty() || patronymic.isEmpty() || phoneNumber.isEmpty() || email.isEmpty() || passwd.isEmpty() || !isEmailValid(email)) {

            Toast.makeText(this, com.mvp_kotlin_login_register_retrofit_example.R.string.please_fill_the_needed_data, Toast.LENGTH_SHORT).show()
            btn_register.isEnabled = true
            registrationPresenter.setProgressBarVisibility(View.INVISIBLE)

            val firstName = input_first_name.text.toString().trim()
            val lastName = input_last_name.text.toString().trim()
            val patronymic = input_patronymic.text.toString().trim()
            val phoneNumber = input_mobile.text.toString().trim()
            val email = input_email.text.toString().trim()
            val passwd = input_password.text.toString().trim()

            if (firstName.isEmpty()) {
                first_name_layout.background = getDrawable(R.drawable.error_fieldbackground)
            }

            if (lastName.isEmpty()) {
                last_name_layout.background = getDrawable(R.drawable.error_fieldbackground)
            }

            if (patronymic.isEmpty()) {
                patronymic_layout.background = getDrawable(R.drawable.error_fieldbackground)
            }

            if (phoneNumber.isEmpty()) {
                mobile_layout.background = getDrawable(R.drawable.error_fieldbackground)
            }

            if (email.isEmpty()) {
                email_layout.background = getDrawable(R.drawable.error_fieldbackground)
            }

            if (passwd.isEmpty()) {
                password_layout.background = getDrawable(R.drawable.error_fieldbackground)
            }

            return false
        } else
            return true
    }

    override fun onSetProgressBarVisibility(visibility: Int) {
        progress_register.visibility = visibility
    }


    override fun onRegistrationResult(result: Boolean, code: Int, identificationToken: String) {
        registrationPresenter.setProgressBarVisibility(View.INVISIBLE)
        if (result && code == 1) {
         Toast.makeText(this, getString(R.string.Registration_Success), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Registration Fail, code = $code", Toast.LENGTH_SHORT).show()
            btn_register.isEnabled = true
        }
    }


    private fun isEmailValid(email: String): Boolean {
        val expression = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(email)
        return matcher.matches()
    }
}
