package com.kolyall.gpspoints.di

import com.kolyall.gpspoints.home.ContributesFeatureModuleHome
import com.kolyall.gpspoints.results.ContributesFeatureModulePointsResult
import com.kolyall.gpspoints.results.PointsResultFragment
import com.kolyall.gpspoints.results.PointsResultFragmentModules
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(
    includes = [
        ContributesFeatureModuleHome::class,
        ContributesFeatureModulePointsResult::class
    ]
)
interface ContributesModule {

}
