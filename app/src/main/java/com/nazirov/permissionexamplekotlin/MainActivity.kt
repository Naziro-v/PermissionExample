package com.nazirov.permissionexamplekotlin

import android.app.Activity
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import java.util.jar.Manifest

const val CODE = 1001

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestPermission()
    }

    private fun hasCameraPermission() =
        ActivityCompat.checkSelfPermission(
            this,
            android.Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED

    private fun hasLocationPermission() =
        ActivityCompat.checkSelfPermission(
            this,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

    private fun hasSmsPermission() =
        ActivityCompat.checkSelfPermission(
            this,
            android.Manifest.permission.RECEIVE_SMS
        ) == PackageManager.PERMISSION_GRANTED

    private fun requestPermission() {
        var permissionToRequest = mutableListOf<String>()
        if (!hasCameraPermission()) {
            permissionToRequest.add(android.Manifest.permission.CAMERA)
        }
          if (!hasLocationPermission()) {
            permissionToRequest.add(android.Manifest.permission.ACCESS_FINE_LOCATION)
        }
          if (!hasSmsPermission()) {
            permissionToRequest.add(android.Manifest.permission.SEND_SMS)
        }
          if (permissionToRequest.isNotEmpty()) {
          ActivityCompat.requestPermissions(this,permissionToRequest.toTypedArray(), CODE)
        }


    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode== CODE && grantResults.isNotEmpty()) {
            for (i in grantResults.indices) {
                if (grantResults[i]==PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this,"${permissions[i] } granted", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}









