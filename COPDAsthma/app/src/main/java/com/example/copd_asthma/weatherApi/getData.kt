package com.example.copd_asthma.weatherApi

import android.util.Log
import com.example.copd_asthma.data.airQuality
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
const val API_KEY = "b98aaa530f0b05621e3fc6d05dec4f8e"

fun getData(lat: Double, lon: Double): airQuality? {
    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
        .create(ApiInterface::class.java)

    val retrofitData = retrofit.getWeatherData(lat, lon, API_KEY)
    var responseBody: airQuality? = null

    retrofitData.enqueue(object : Callback<airQuality> {

        override fun onResponse(call: Call<airQuality>, response: Response<airQuality>) {
            responseBody = response.body()!!
            Log.d("HTTPSuccess", responseBody!!.myList[0].components.co.toString())
        }

        override fun onFailure(call: Call<airQuality>, t: Throwable) {
            Log.d("HTTPFail", t.toString())
        }
    })
    return if (responseBody!= null) {
        responseBody
    }
    else {
        null
    }
}

