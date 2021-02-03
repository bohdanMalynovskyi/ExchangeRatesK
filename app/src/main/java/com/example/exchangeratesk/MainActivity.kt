package com.example.exchangeratesk

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import com.example.exchangeratesk.adapter.RateAdapter
import com.example.exchangeratesk.fragment.SelectedCurrencyRateFragment
import com.example.exchangeratesk.model.RatesByBaseCurrency
import com.example.exchangeratesk.retrofit.RetrofitService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), AdapterView.OnItemClickListener {
    lateinit var currencyList: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvBaseCurrency.text = getString(R.string.base_currency_heading, Constants.BASE_CURRENCY)

        loadDataFromWebService()


//        ratesListRecyclerView.
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

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val intent = Intent(this, SelectedCurrencyRateFragment::class.java)
        intent.putExtra(Constants.CUR_NAME, currencyList[position])
        startActivity(intent)
    }
}