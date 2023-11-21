package com.module.data.geopoint

import com.module.domain.PointsPackRepository
import com.module.domain.PointsPack
import javax.inject.Inject

class PointsPackRepositoryImpl @Inject constructor(
    private val remotePointsPackRepository: RemotePointsPackRepository,
    private val dbPointsPackRepository: DbPointsPackRepository
) : PointsPackRepository {

    override suspend fun fetchPointsPack(count: Int) {
        val pointsPack = remotePointsPackRepository.getPointsPack(count=count)
        dbPointsPackRepository.storePointsPack(pointsPack)
    }

    override suspend fun getPointsPack(): PointsPack {
        return dbPointsPackRepository.getPointsPack()
    }

}

