package com.example.exchangeratesk.model

data class ExchangeRate(
    var rates: Map<String, Double>?,
    var base: String?,
    var date: String?
)