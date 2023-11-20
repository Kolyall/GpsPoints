package com.module.data.geopoint

import com.module.domain.PointsPack

interface DbGeoPointRepository{
    suspend fun storePointsPack(pointsPack: PointsPack)
    suspend fun getList(): PointsPack
}