package com.module.remote.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PointsResponseDTO(
    @Expose
    @SerializedName("points")
    val points: List<PointDTO>
)