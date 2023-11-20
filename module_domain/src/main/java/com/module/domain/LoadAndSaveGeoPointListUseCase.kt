package com.module.domain

import javax.inject.Inject

class LoadAndSaveGeoPointListUseCase @Inject constructor(
    private val geoPointRepository: GeoPointRepository
) {

    suspend operator fun invoke(count: Int) {
        geoPointRepository.fetchList(count = count)
    }
}