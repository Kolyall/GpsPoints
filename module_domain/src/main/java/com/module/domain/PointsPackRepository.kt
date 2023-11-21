package com.module.domain

interface PointsPackRepository {
    suspend fun fetchPointsPack(count: Int)
    suspend fun getPointsPack(): PointsPack
}