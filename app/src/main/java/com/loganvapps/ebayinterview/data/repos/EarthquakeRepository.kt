package com.loganvapps.ebayinterview.data.repos

import com.google.gson.GsonBuilder
import com.loganvapps.ebayinterview.data.models.Earthquakes
import com.loganvapps.ebayinterview.networking.EarthquakeService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EarthquakeRepository() {

    val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()


    val gson = GsonBuilder().setLenient().create()
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://api.geonames.org/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    private val service = retrofit.create(EarthquakeService::class.java)

    suspend fun getEarthquakes():Earthquakes?{
        val response = service.getEarthquakes(true, 54.1 ,-9.9, 32.4,55.2,"mkoppelman")
        return try {
            if(response.isSuccessful){
                response.body()
            }else{
                null
            }
        }catch (e:Exception){
            null
        }
    }

}