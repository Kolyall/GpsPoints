package com.kolyall.gpspoints.home

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ContributesFeatureModuleHome {

    @ContributesAndroidInjector(modules = [HomeFragmentModules::class])
    fun homeFragment(): HomeFragment
}
