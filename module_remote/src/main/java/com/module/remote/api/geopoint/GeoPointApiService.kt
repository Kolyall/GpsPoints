package com.module.remote.api.geopoint

import com.module.remote.api.models.PointsResponseDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface GeoPointApiService {
    @GET("/api/test/points")
    suspend fun getPoints(@Query("count") count: Int): PointsResponseDTO
}

