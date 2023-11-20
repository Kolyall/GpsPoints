package com.kolyall.gpspoints.results

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ContributesFeatureModulePointsResult {

    @ContributesAndroidInjector(modules = [PointsResultFragmentModules::class])
    fun pointsResultFragment(): PointsResultFragment
}
