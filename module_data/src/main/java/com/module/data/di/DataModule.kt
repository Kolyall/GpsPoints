package com.module.data.di

import com.module.data.geopoint.PointsPackRepositoryImpl
import com.module.domain.PointsPackRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    abstract fun bindsPointsPackRepository(repository: PointsPackRepositoryImpl): PointsPackRepository
}