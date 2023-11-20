package com.module.data.di

import com.module.data.geopoint.GeoPointRepositoryImpl
import com.module.domain.GeoPointRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    abstract fun bindsGeoPointRepository(repository: GeoPointRepositoryImpl): GeoPointRepository
}