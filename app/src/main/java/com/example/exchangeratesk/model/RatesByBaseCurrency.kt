package com.example.exchangeratesk.model

data class RatesByBaseCurrency(
    var rates: Map<String, Double>?,
    var base: String?,
    var date: String?
)