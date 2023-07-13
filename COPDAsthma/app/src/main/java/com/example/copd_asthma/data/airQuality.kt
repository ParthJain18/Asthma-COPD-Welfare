package com.example.copd_asthma.data

import com.google.gson.annotations.SerializedName

data class airQuality(
    val coord: Coord,
    @SerializedName("list")
    val myList: List<Item>
)