package com.example.exchangeratesk

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

//    fun loadDataFromWebService(){
//        ExchangeRatesApi.create()
//            .getRatesByBaseCurrency(Constants.BASE_CURRENCY)
//            .enqueue(object: Callback<RatesByBaseCurrency>)
//    }
}