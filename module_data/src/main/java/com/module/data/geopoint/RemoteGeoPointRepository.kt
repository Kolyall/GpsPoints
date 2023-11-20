package com.module.data.geopoint

import com.module.domain.PointsPack

interface RemoteGeoPointRepository{
    suspend fun getPointsPack(count: Int): PointsPack
}