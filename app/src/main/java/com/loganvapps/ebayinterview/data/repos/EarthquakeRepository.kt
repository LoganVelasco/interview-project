package com.loganvapps.ebayinterview.data.repos

import com.google.gson.GsonBuilder
import com.loganvapps.ebayinterview.data.models.Earthquake
import com.loganvapps.ebayinterview.networking.EarthquakeService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EarthquakeRepository() {
    val gson = GsonBuilder().setLenient().create()
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://api.geonames.org/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    private val service = retrofit.create(EarthquakeService::class.java)

    suspend fun getEarthquakes():List<Earthquake>?{
        val response = service.getEarthquakes(true, 54.1 ,-9.9, 32.4,55.2,"mkoppelman")
        return try {
            if(response.isSuccessful){
                response.body()?.earthquakes
            }else{
                null
            }
        }catch (e:Exception){
            null
        }
    }

}