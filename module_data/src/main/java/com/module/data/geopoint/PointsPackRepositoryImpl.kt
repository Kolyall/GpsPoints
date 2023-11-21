package com.module.data.geopoint

import com.module.domain.PointsPack
import com.module.domain.PointsPackRepository
import javax.inject.Inject

class PointsPackRepositoryImpl @Inject constructor(
    private val remotePointsPackRepository: RemotePointsPackRepository,
    private val dbPointsPackRepository: DbPointsPackRepository
) : PointsPackRepository {

    override suspend fun fetchPointsPack(count: Int) {
        val pointsPack = loadPointsPack(count = count)
        dbPointsPackRepository.storePointsPack(pointsPack)
    }

    private suspend fun loadPointsPack(count: Int): PointsPack {
        val list = remotePointsPackRepository.getPoints(count = count)
            .sortedBy { it.x.value }

        return PointsPack(
            id = list.map { "${it.x.value}:${it.y.value}" }
                .reduceRight { acc, item -> "$acc;$item" },
            list = list
        )
    }

    override suspend fun getPointsPack(): PointsPack {
        return dbPointsPackRepository.getPointsPack()
    }

}

