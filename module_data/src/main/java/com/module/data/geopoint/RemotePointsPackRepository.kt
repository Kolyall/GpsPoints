package com.module.data.geopoint

import com.module.domain.PointsPack

interface RemotePointsPackRepository{
    suspend fun getPointsPack(count: Int): PointsPack
}