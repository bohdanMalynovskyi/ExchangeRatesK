package com.example.exchangeratesk

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.exchangeratesk.adapter.RateAdapter
import com.example.exchangeratesk.model.RatesByBaseCurrency
import com.example.exchangeratesk.retrofit.RetrofitService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadDataFromWebService()
    }

    private fun loadDataFromWebService() {
        RetrofitService.create()
            .getRatesByBaseCurrency(Constants.BASE_CURRENCY)
            .enqueue(object : Callback<RatesByBaseCurrency> {
                override fun onResponse(
                    call: Call<RatesByBaseCurrency>,
                    response: Response<RatesByBaseCurrency>
                ) {
                    ratesListRecyclerView.adapter = RateAdapter(response.body()?.rates!!)
                }

                override fun onFailure(call: Call<RatesByBaseCurrency>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
    }
}