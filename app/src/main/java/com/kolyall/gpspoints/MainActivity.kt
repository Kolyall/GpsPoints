package com.kolyall.gpspoints

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.core.presentation.navcontroller.findNavControllerCompat
import com.kolyall.gpspoints.home.HomeFragment
import com.kolyall.gpspoints.home.HomeFragmentDirections

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
}


