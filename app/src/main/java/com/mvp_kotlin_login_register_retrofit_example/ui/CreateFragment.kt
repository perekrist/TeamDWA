package com.mvp_kotlin_login_register_retrofit_example.ui

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v4.app.Fragment
import android.arch.lifecycle.ViewModelProviders
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import android.os.Looper
import android.provider.MediaStore
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat.getSystemService
import android.support.v7.app.AlertDialog
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.mvp_kotlin_login_register_retrofit_example.AppConstants
import com.mvp_kotlin_login_register_retrofit_example.R
import com.mvp_kotlin_login_register_retrofit_example.view.FormActivity
import com.mvp_kotlin_login_register_retrofit_example.view.RegistrationActivity
import kotlinx.android.synthetic.main.activity_take_photo.*
import kotlinx.android.synthetic.main.activity_take_photo.imageView
import kotlinx.android.synthetic.main.activity_take_photo.takePhoto
import kotlinx.android.synthetic.main.fragment_create.*
import kotlin.random.Random

class CreateFragment : Fragment() {

    var fileUri: Uri? = null
    val PERMISSION_ID = 42
    var percent_ = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_create, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        takePhoto.setOnClickListener {
            askCameraPermission()
        }

        go_to_form.setOnClickListener {
            if (percent_ >= 70) {
                val intent = Intent(context , FormActivity::class.java)
                startActivity(intent)
            }
        }

    }

    private fun launchCamera() {
        val values = ContentValues(1)
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpg")
        fileUri = context!!.contentResolver.insert(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            values)
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if(intent.resolveActivity(context!!.packageManager) != null) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri)
            intent.addFlags(
                Intent.FLAG_GRANT_READ_URI_PERMISSION
                        or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
            startActivityForResult(intent,
                AppConstants.TAKE_PHOTO_REQUEST
            )
        }
    }

    fun askCameraPermission(){
        Dexter.withActivity(context as Activity)
            .withPermissions(
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {/* ... */
                    if(report.areAllPermissionsGranted()){
                        launchCamera()
                    }else{
                        Toast.makeText(context, "All permissions need to be granted to take photo", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onPermissionRationaleShouldBeShown(permissions: List<PermissionRequest>, token: PermissionToken) {/* ... */
                    //show alert dialog with permission options
                    AlertDialog.Builder(context!!)
                        .setTitle(
                            "Permissions Error!")
                        .setMessage(
                            "Please allow permissions to take photo with camera")
                        .setNegativeButton(
                            android.R.string.cancel
                        ) { dialog, _ ->
                            dialog.dismiss()
                            token.cancelPermissionRequest()
                        }
                        .setPositiveButton(android.R.string.ok
                        ) { dialog, _ ->
                            dialog.dismiss()
                            token.continuePermissionRequest()
                        }
                        .setOnDismissListener {
                            token.cancelPermissionRequest()
                        }
                        .show()
                }

            }).check()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int,
                                  data: Intent?) {
        if (resultCode == Activity.RESULT_OK
            && requestCode == AppConstants.TAKE_PHOTO_REQUEST
        ) {
            Glide.with(this)
                .load(fileUri)
                .into(imageView)
            percent_ = (60..99).random()
            percent.text = "$percent_%"

        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }


}