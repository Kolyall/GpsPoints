package com.kolyall.gpspoints

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.core.presentation.navcontroller.findNavControllerCompat
import com.kolyall.gpspoints.home.HomeFragment
import com.kolyall.gpspoints.home.HomeFragmentDirections
import com.kolyall.gpspoints.results.PointsResultFragment

class MainActivity : AppCompatActivity(), HomeFragment.OnSuccessListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onHomeSuccess() {
        findNavControllerCompat(R.id.main_nav_controller)?.navigate(
            HomeFragmentDirections.actionHomeFragmentToRestoreFragment()
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}


