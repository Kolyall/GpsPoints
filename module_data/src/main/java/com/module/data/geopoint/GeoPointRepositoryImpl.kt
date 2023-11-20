package com.module.data.geopoint

import com.module.domain.GeoPointRepository
import com.module.domain.PointsPack
import javax.inject.Inject

class GeoPointRepositoryImpl @Inject constructor(
    private val remoteGeoPointRepository: RemoteGeoPointRepository,
    private val dbGeoPointRepository: DbGeoPointRepository
) : GeoPointRepository {

    override suspend fun fetchList(count: Int) {
        val pointsPack = remoteGeoPointRepository.getPointsPack(count=count)
        dbGeoPointRepository.storePointsPack(pointsPack)
    }

    override suspend fun getList(): PointsPack {
        return dbGeoPointRepository.getList()
    }

}

