package com.module.remote

import com.module.data.geopoint.RemoteGeoPointRepository
import com.module.domain.Point
import com.module.domain.PointsPack
import com.module.domain.X
import com.module.domain.Y
import com.module.remote.api.geopoint.GeoPointApiService
import javax.inject.Inject

class RemoteGeoPointRepositoryImpl @Inject constructor(private val apiService: GeoPointApiService) :
    RemoteGeoPointRepository {
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
    override suspend fun getPointsPack(count: Int): PointsPack {
        return PointsPack(
            list = apiService.getPoints(count = count)
                .points.map {
                    Point(
                        X(it.x),
                        Y(it.y),
                    )
                }
                .sortedBy { it.x.value }
        )
    }
}