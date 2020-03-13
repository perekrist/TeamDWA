package com.mvp_kotlin_login_register_retrofit_example.view

import android.content.Intent
import android.content.SharedPreferences
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
//import com.google.android.gms.location.FusedLocationProviderClient
//import com.google.android.gms.location.LocationCallback
//import com.google.android.gms.location.LocationRequest
//import com.google.android.gms.location.LocationServices
import com.mvp_kotlin_login_register_retrofit_example.R
import com.mvp_kotlin_login_register_retrofit_example.classes.PersonData
import com.mvp_kotlin_login_register_retrofit_example.classes.SituationModel
import kotlinx.android.synthetic.main.activity_form.*
import kotlinx.android.synthetic.main.content_form.*
import java.text.SimpleDateFormat
import java.util.*

class FormActivity : AppCompatActivity() {

    private val mPrefs: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(
            this
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)
        buttonSubmitForm.setOnClickListener {
//            val newRec = SituationModel()
//            newRec.comment = editFormDescription.text.toString()
//            val someID = mPrefs.getInt("id", 0)
//            newRec.id = someID
//            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
//            val currentDate = sdf.format(Date())
//            newRec.datatime = currentDate
//            newRec.latitude = 37f
//            newRec.longitude = 122f
//            awesomeSender(newRec)
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }

        buttonCancel.setOnClickListener {
            finish()
        }
    }

    fun awesomeSender(s: SituationModel) {

    }
//
//    private fun requestNewLocationData() {
//        var mLocationRequest = LocationRequest()
//        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
//        mLocationRequest.interval = 0
//        mLocationRequest.fastestInterval = 0
//        mLocationRequest.numUpdates = 1
//
//        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
//        fusedLocationClient.requestLocationUpdates(
//            mLocationRequest, LocationCallback(),
//            Looper.myLooper()
//        )
//    }
//
//    override fun onResume() {
//        super.onResume()
//
//        val someID = mPrefs.getInt("id", 0)
//        val pr = awesomePersonGet(someID)
//        textView7.text = pr.surnname
//        textView8.text = pr.nameReal
//        textView9.text = pr.parentific
//        textView10.text = pr.email
//        textView11.text = pr.phone
//    }
//
//    fun awesomePersonGet(id: Int): PersonData {
//        val np = PersonData(
//            "Victor",
//            "Victorov",
//            "Victorovich",
//            "Vic1929@crash.uk",
//            "17-70-13"
//        )
//        return np
//    }

}
