package com.module.domain

import javax.inject.Inject

class GetPointsPackUseCase @Inject constructor(
    private val pointsPackRepository: PointsPackRepository
) {

    suspend operator fun invoke(): PointsPack {
        return pointsPackRepository.getPointsPack()
    }
}