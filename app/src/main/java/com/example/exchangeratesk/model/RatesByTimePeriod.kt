package com.example.exchangeratesk.model

data class RatesByTimePeriod(
    var rates: Map<String, Map<String, Double>>?,
    var start_at: String?,
    var base: String?,
    var end_at: String?
)