package com.example.exchangeratesk.retrofit

import com.example.exchangeratesk.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    private var retrofit: Retrofit? = null

    fun getRetrofitService(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }

    fun getExchangeRatesApi(): ExchangeRatesApi {
        return getRetrofitService().create(ExchangeRatesApi::class.java)
    }
}