package com.module.domain

import javax.inject.Inject

class GetPointListUseCase @Inject constructor(
    private val geoPointRepository: GeoPointRepository
) {

    suspend operator fun invoke(): PointsPack {
        return geoPointRepository.getList()
    }
}