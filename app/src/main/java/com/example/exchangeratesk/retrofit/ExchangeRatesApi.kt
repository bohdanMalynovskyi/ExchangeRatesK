package com.example.exchangeratesk.retrofit

import com.example.exchangeratesk.Constants
import com.example.exchangeratesk.model.RatesByBaseCurrency
import com.example.exchangeratesk.model.RatesByTimePeriod
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

    companion object {
        fun create(): ExchangeRatesApi {
            val retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ExchangeRatesApi::class.java)
        }
    }
}