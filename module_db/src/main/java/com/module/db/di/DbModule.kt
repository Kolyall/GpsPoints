package com.module.db.di

import com.module.data.geopoint.DbGeoPointRepository
import com.module.data.geopoint.RemoteGeoPointRepository
import com.module.db.repo.DbGeoPointRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DbModule {

    @Binds
    @Singleton
    abstract fun bindsRemoteGeoPointRepository(
        repository: DbGeoPointRepositoryImpl
    ): DbGeoPointRepository
}