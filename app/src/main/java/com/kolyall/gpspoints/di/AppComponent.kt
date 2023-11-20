package com.kolyall.gpspoints.di

import android.app.Application
import com.core.presentation.ViewModelFactoryModule
import com.kolyall.gpspoints.TheApplication
import com.module.data.di.DataModule
import com.module.db.di.DbModule
import com.module.remote.di.RemoteModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ViewModelFactoryModule::class,
        ContributesModule::class,
        DataModule::class,
        RemoteModule::class,
        DbModule::class,
    ]
)
interface AppComponent : AndroidInjector<TheApplication> {

    @Component.Factory
    interface Builder {

        fun create(
            @BindsInstance
            application: Application
        ): AppComponent
    }
}
