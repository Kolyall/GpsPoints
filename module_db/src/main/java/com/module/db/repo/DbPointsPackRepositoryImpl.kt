package com.module.db.repo

import com.module.data.geopoint.DbPointsPackRepository
import com.module.domain.PointsPack
import javax.inject.Inject

class DbPointsPackRepositoryImpl @Inject constructor() : DbPointsPackRepository {

    private var pointsPack: PointsPack? = null
    override suspend fun storePointsPack(pointsPack: PointsPack) {
        this.pointsPack = pointsPack
    }

    override suspend fun getPointsPack(): PointsPack {
        return pointsPack ?: throw RuntimeException("Not loaded!")
    }
}