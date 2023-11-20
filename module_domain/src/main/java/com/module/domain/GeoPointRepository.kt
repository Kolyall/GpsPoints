package com.module.domain

interface GeoPointRepository {
    suspend fun fetchList(count: Int)
    suspend fun getList(): PointsPack
}