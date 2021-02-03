package com.example.exchangeratesk.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.exchangeratesk.R
import java.text.SimpleDateFormat
import java.util.*

class SelectedCurrencyRateFragment : Fragment() {
    private val milSecInDay = 1000 * 60 * 60 * 24.toLong()
    private val formatter: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
    private val yesterdayDate: String = formatter.format(Date())
    private val weekAgoDate: String =
        formatter.format(Date(System.currentTimeMillis() - milSecInDay * 7))
    lateinit var baseCurrency: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_selected_currency_rate, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        baseCurrency =
    }

    fun loadData() {
//        RetrofitService.create()
//            .getRatesByTimePeriod(yesterdayDate, weekAgoDate, Constants.BASE_CURRENCY, )
    }
}