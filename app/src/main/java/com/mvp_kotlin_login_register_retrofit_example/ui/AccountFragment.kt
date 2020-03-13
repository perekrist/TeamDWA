package com.mvp_kotlin_login_register_retrofit_example.ui

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.support.v4.app.Fragment
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.widget.ImageView
import android.widget.Toast
import com.mvp_kotlin_login_register_retrofit_example.R
import com.mvp_kotlin_login_register_retrofit_example.presenter.implemenation.AccountPresenterImpl
import com.mvp_kotlin_login_register_retrofit_example.presenter.implemenation.LoginPresenterImpl
import com.mvp_kotlin_login_register_retrofit_example.presenter.implemenation.ResponseAcc
import com.mvp_kotlin_login_register_retrofit_example.presenter.interfaces.IAccountPresenter
import com.mvp_kotlin_login_register_retrofit_example.presenter.interfaces.ILoginPresenter
import com.mvp_kotlin_login_register_retrofit_example.view.RegistrationActivity
import com.mvp_kotlin_login_register_retrofit_example.view.interfaces.IAccountView
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_account.*

class AccountFragment : Fragment(), IAccountView {

    private lateinit var accountPresenter: IAccountPresenter
    private val mPrefs: SharedPreferences by lazy { PreferenceManager.getDefaultSharedPreferences(context) }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_account, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()

    }

    private fun setupView() {
        accountPresenter = AccountPresenterImpl(context as IAccountView , context!!)
        accountPresenter.setProgressBarVisibility(View.INVISIBLE)
        accountPresenter.getAccountData(mPrefs.getInt("id", 0))

    }

    override fun onAccountResult(result: Boolean, code: Int, response: ResponseAcc) {
        if (result && code == 1) {
            user_first_name.text = response.user_first_name
            user_last_name.text = response.user_last_name
            user_patronymic.text = response.user_patronymic
            user_email.text = response.user_email
            user_phone.text = response.user_phone

        } else {
            Toast.makeText(context,  getString(R.string.please_try_again), Toast.LENGTH_SHORT).show()
            btn_login.isEnabled = true
            input_password.text.clear()
        }
    }

    override fun onSetProgressBarVisibility(visibility: Int) {

    }
}