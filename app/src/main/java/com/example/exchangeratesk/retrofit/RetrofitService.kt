package com.example.exchangeratesk.retrofit

import com.example.exchangeratesk.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    fun create(): ExchangeRatesApi {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ExchangeRatesApi::class.java)
    }
}