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
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter
import com.jjoe64.graphview.series.LineGraphSeries
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class SelectedCurrencyRateFragment : Fragment() {
    var ratesByTimePeriod: Map<String, Map<String, Double>>? = null

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
                    ratesByTimePeriod = response.body()?.rates
                    val timePeriodDates: MutableList<String> =
                        ArrayList(ratesByTimePeriod?.keys)

                    val sortedRatesByTimePeriod: ArrayList<Map<String, Double>> =
                        sortingOfRatesByTimePeriod(timePeriodDates)

                    graphBuilding(sortedRatesByTimePeriod)
                }

                override fun onFailure(call: Call<RatesByTimePeriod>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
    }

    private fun sortingOfRatesByTimePeriod(ratesDates: MutableList<String>): ArrayList<Map<String, Double>> {
        var comparedDate1: Date? = null
        var comparedDate2: Date? = null
        for (i in ratesDates.size - 1 downTo 1) {
            for (j in 0 until i) {
                try {
                    comparedDate1 = formatter.parse(ratesDates[j])
                    comparedDate2 = formatter.parse(ratesDates[j + 1])
                } catch (e: java.lang.Exception) {

                }
                if (comparedDate1!!.after(comparedDate2)) {
                    val s = ratesDates[j]
                    ratesDates[j] = ratesDates[j + 1]
                    ratesDates[j + 1] = s
                }
            }
        }
        val sortedRatesByDates: ArrayList<Map<String, String>> =
            ArrayList(ratesDates.size)
        var map: MutableMap<*, *>
        for (i in ratesDates.indices) {
            map = HashMap()
            map[ATTR_RATE_DATE] = ratesDates[i]
            map[GlobalVariables.ATTR_EX_RATE] = ratesByTimePeriod!![ratesDates[i]]!![curName]
            sortedRatesByDates.add(map)
        }
        return sortedRatesByDates
    }

    private fun graphBuilding(graphData: ArrayList<Map<String, Double>>) {
        val graph: GraphView = findViewById(R.id.graph)
        val datesForGraph = arrayOfNulls<Date>(graphData.size())
        try {
            for (i in 0 until graphData.size()) {
                datesForGraph[i] = formatter.parse(graphData[i][ATTR_RATE_DATE])
            }
        } catch (e: Exception) {
            Log.e(GlobalVariables.TAG, e.message)
        }
        val dataPoints: Array<DataPoint?> = arrayOfNulls<DataPoint>(graphData.size())
        for (i in 0 until graphData.size()) {
            dataPoints[i] = DataPoint(
                datesForGraph[i],
                graphData[i][GlobalVariables.ATTR_EX_RATE]!!.toDouble()
            )
        }
        val series: LineGraphSeries<DataPoint?> = LineGraphSeries<E?>(dataPoints)
        series.setDrawDataPoints(true)
        graph.addSeries(series)
        graph.gridLabelRenderer.labelFormatter = DateAsXAxisLabelFormatter(this)
        graph.gridLabelRenderer.numHorizontalLabels = graphData.size()
        graph.gridLabelRenderer.setHorizontalLabelsAngle(90)
        graph.viewport.isXAxisBoundsManual = true
        graph.viewport.setMinX(datesForGraph[0]!!.time.toDouble())
        graph.viewport.setMaxX(datesForGraph[datesForGraph.size - 1]!!.time.toDouble())
        tvDates.setText(
            "Rates for " + graphData[0][ATTR_RATE_DATE].toString() + " - " + graphData[graphData.size() - 1][ATTR_RATE_DATE]
        )
    }

}