package com.example.copd_asthma.data.airQuality

import com.google.gson.annotations.SerializedName

data class airQuality(
    val coord: Coord,
    @SerializedName("list")
    val myList: List<Item>,
    var safety: String = "Fetching..",
    var airQualityName: String = ""
)