package com.module.domain

import javax.inject.Inject

class FetchPointsPackUseCase @Inject constructor(
    private val pointsPackRepository: PointsPackRepository
) {

    suspend operator fun invoke(count: Int) {
        pointsPackRepository.fetchPointsPack(count = count)
    }
}