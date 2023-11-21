package com.module.data.geopoint

import com.module.domain.PointsPack

interface DbPointsPackRepository{
    suspend fun storePointsPack(pointsPack: PointsPack)
    suspend fun getPointsPack(): PointsPack
}