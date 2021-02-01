package com.example.exchangeratesk.holder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.exchangeratesk.R

class RateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvRateCurName: TextView = itemView.findViewById(R.id.exchange_rate_currency_name)
    val tvRateCurValue: TextView = itemView.findViewById(R.id.exchange_rate_currency_value)
}