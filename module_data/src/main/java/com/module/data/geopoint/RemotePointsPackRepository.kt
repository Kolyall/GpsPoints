package com.module.data.geopoint

import com.module.domain.Point

interface RemotePointsPackRepository{
    suspend fun getPoints(count: Int): List<Point>
}