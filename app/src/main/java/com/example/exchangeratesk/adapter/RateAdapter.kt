package com.example.exchangeratesk.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exchangeratesk.R
import com.example.exchangeratesk.holder.RateViewHolder

class RateAdapter(private val ratesList: Map<String, Double>) :
    RecyclerView.Adapter<RateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RateViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.exchange_rate_item, parent, false)
        return RateViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return ratesList.size
    }

    override fun onBindViewHolder(holder: RateViewHolder, position: Int) {
        val currencyNames: List<String>? = ratesList.keys.toList()
        holder.tvRateCurName.text = currencyNames!![position]
        holder.tvRateCurValue.text = ratesList[currencyNames[position]].toString()
    }
}