package com.example.exchangeratesk.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.exchangeratesk.Constants
import com.example.exchangeratesk.R
import com.example.exchangeratesk.model.RatesByTimePeriod
import com.example.exchangeratesk.retrofit.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class SelectedCurrencyRateFragment : Fragment() {
    private val milSecInDay = 1000 * 60 * 60 * 24.toLong()
    private val formatter: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
    private val yesterdayDate: String = formatter.format(Date())
    private val weekAgoDate: String =
        formatter.format(Date(System.currentTimeMillis() - milSecInDay * 7))
    lateinit var curName: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_selected_currency_rate, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        curName = activity?.intent?.getStringExtra(Constants.CUR_NAME).toString()
    }

    private fun loadDataFromWebService() {
        RetrofitService.create()
            .getRatesByTimePeriod(yesterdayDate, weekAgoDate, Constants.BASE_CURRENCY, curName)
            .enqueue(object : Callback<RatesByTimePeriod> {
                override fun onResponse(
                    call: Call<RatesByTimePeriod>,
                    response: Response<RatesByTimePeriod>
                ) {
                    TODO("Not yet implemented")
                }

                override fun onFailure(call: Call<RatesByTimePeriod>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
    }

}