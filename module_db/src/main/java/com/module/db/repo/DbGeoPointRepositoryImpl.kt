package com.module.db.repo

import com.module.data.geopoint.DbGeoPointRepository
import com.module.domain.PointsPack
import javax.inject.Inject

class DbGeoPointRepositoryImpl @Inject constructor() : DbGeoPointRepository {

    private var pointsPack: PointsPack? = null
    override suspend fun storePointsPack(pointsPack: PointsPack) {
        this.pointsPack = pointsPack
    }

    override suspend fun getList(): PointsPack {
        return pointsPack ?: throw RuntimeException("Not loaded!")
    }
}