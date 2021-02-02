package com.example.exchangeratesk.retrofit

import com.example.exchangeratesk.model.RatesByBaseCurrency
import com.example.exchangeratesk.model.RatesByTimePeriod
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ExchangeRatesApi {

    @GET("/latest")
    fun getRatesByBaseCurrency(@Query("base") base: String): Call<RatesByBaseCurrency>

    @GET("/history")
    fun getRatesByTimePeriod(
        @Query("start_at") start_at: String,
        @Query("end_at") end_at: String,
        @Query("base") base: String,
        @Query("symbols") symbols: String
    ): Call<RatesByTimePeriod>
}