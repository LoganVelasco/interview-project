package com.loganvapps.ebayinterview.networking

import com.loganvapps.ebayinterview.data.models.Earthquake
import com.loganvapps.ebayinterview.data.models.Earthquakes
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface EarthquakeService {

    // Adding this fixed the crash, Looks like it was returning xml instead of json
    @Headers("Accept: application/json")
    @GET("earthquakes")
    suspend fun getEarthquakes(@Query("formatted")formatted: Boolean, @Query("north")north: Double,
                               @Query("south")south: Double, @Query("east")east: Double, @Query("west")west: Double,
                               @Query("username")username: String): Response<Earthquakes>

}