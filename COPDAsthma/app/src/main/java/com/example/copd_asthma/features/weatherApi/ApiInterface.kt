package com.example.copd_asthma.features.weatherApi

import com.example.copd_asthma.data.airQuality.airQuality
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


//https://api.openweathermap.org/data/2.5/air_pollution?lat=19.075983&lon=72.877655&appid=b98aaa530f0b05621e3fc6d05dec4f8e

interface ApiInterface {

    @GET("air_pollution")
    fun getWeatherData(@Query("lat") lat: Double, @Query("lon") lon: Double, @Query("appid") appid: String):
            Call<airQuality>

}