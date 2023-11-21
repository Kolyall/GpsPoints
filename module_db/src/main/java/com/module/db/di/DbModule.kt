package com.module.db.di

import com.module.data.geopoint.DbPointsPackRepository
import com.module.db.repo.DbPointsPackRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DbModule {

    @Binds
    @Singleton
    abstract fun bindsDbPointsPackRepository(
        repository: DbPointsPackRepositoryImpl
    ): DbPointsPackRepository
}