package com.module.remote

import com.module.data.geopoint.RemotePointsPackRepository
import com.module.domain.Point
import com.module.domain.PointsPack
import com.module.domain.X
import com.module.domain.Y
import com.module.remote.api.geopoint.GeoPointApiService
import javax.inject.Inject

class RemotePointsPackRepositoryImpl @Inject constructor(private val apiService: GeoPointApiService) :
    RemotePointsPackRepository {
    //    override suspend fun loadList(): Points {
//        return Points(
//            (0..100).map {
//                Point(
//                    x = X(it),
//                    y = Y(it)
//                )
//            }
//        )
//    }
    override suspend fun getPoints(count: Int): List<Point> {
        return apiService.getPoints(count = count)
            .points.map {
                Point(
                    X(it.x),
                    Y(it.y),
                )
            }
    }
}