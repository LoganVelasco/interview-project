package com.loganvapps.ebayinterview.data.models

data class Earthquake (
    val datetime: String,
    val depth: Double,
    val lng: Double,
    val src: String,
    val eqid: String,
    val magnitude: Double,
    val lat: Double
)
data class Earthquakes(
    val earthquakes: List<Earthquake>
)