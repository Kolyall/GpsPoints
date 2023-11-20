package com.kolyall.gpspoints

import android.app.Application
import com.kolyall.gpspoints.di.AppComponent
import com.kolyall.gpspoints.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class TheApplication: DaggerApplication() {
    private lateinit var component: AppComponent
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        component = DaggerAppComponent.factory().create(this)
        component.inject(this)
        return component
    }
}