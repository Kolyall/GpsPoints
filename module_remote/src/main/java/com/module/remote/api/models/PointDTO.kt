package com.module.remote.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PointDTO(
    @Expose
    @SerializedName("x")
    val x: Double,
    @Expose
    @SerializedName("y")
    val y: Double
)