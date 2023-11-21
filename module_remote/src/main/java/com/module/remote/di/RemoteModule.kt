package com.module.remote.di

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.module.data.geopoint.RemotePointsPackRepository
import com.module.remote.RemotePointsPackRepositoryImpl
import com.module.remote.api.geopoint.GeoPointApiService
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(
    includes = [
        RemoteRepositoryModule::class,
        RemoteApiModule::class,
    ]
)
 class RemoteModule {

}

@Module
abstract class RemoteRepositoryModule {

    @Binds
    abstract fun bindsRemotePointsPackRepository(
        repository: RemotePointsPackRepositoryImpl
    ): RemotePointsPackRepository
}

@Module
class RemoteApiModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .setLenient()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS).build()
    }

    @Provides
    fun providesGeoPointApiService(
        gson: Gson,
        okHttpClient: OkHttpClient
    ): GeoPointApiService {
        return Retrofit.Builder()
            .baseUrl("http://hr-challenge.interactive-ventures.com")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(GeoPointApiService::class.java) as GeoPointApiService
    }
}