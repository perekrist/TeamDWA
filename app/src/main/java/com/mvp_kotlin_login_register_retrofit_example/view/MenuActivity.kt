package com.mvp_kotlin_login_register_retrofit_example.view

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import android.support.v4.widget.DrawerLayout
import android.support.design.widget.NavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.Menu
import com.mvp_kotlin_login_register_retrofit_example.R
import com.mvp_kotlin_login_register_retrofit_example.classes.PersonData
import com.mvp_kotlin_login_register_retrofit_example.classes.currenPerson
import com.mvp_kotlin_login_register_retrofit_example.classes.posts
import com.mvp_kotlin_login_register_retrofit_example.presenter.implemenation.ResponseAcc
import com.mvp_kotlin_login_register_retrofit_example.presenter.implemenation.ResponseHistory
import com.mvp_kotlin_login_register_retrofit_example.ui.Adapter
import com.mvp_kotlin_login_register_retrofit_example.view.interfaces.IAccountView
import com.mvp_kotlin_login_register_retrofit_example.view.interfaces.IHistoryView
import kotlinx.android.synthetic.main.fragment_account.*
import kotlinx.android.synthetic.main.fragment_list.*

class MenuActivity : AppCompatActivity(), IAccountView, IHistoryView {
    override fun onHistoryResult(result: Boolean, code: Int, response: ResponseHistory) {
        rv_history.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_history.adapter = Adapter(this, posts)
    }

    override fun onSetProgressBarVisibility(visibility: Int) {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAccountResult(result: Boolean, code: Int, response: ResponseAcc) {
        user_first_name.text = response.user_first_name
        user_last_name.text = response.user_last_name
        user_patronymic.text = response.user_patronymic
        user_email.text = response.user_email
        user_phone.text = response.user_phone

        currenPerson = PersonData(
            response.user_first_name!!,
            response.user_last_name!!,
            response.user_patronymic!!,
            response.user_email!!,
            response.user_phone!!

        )
    }


    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_history,
                R.id.nav_account,
                R.id.nav_create,
                R.id.nav_sign_out
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
