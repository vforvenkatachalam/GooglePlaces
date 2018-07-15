package com.example.chalam.googleplaces

import com.example.chalam.googleplaces.beans.PlacesBean
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GooglePlacesAPI {
    @GET("maps/api/place/nearbysearch/json?location=17.4376097,78.4491694&radius=500&key=YOUR_KEY")
    fun getPlacesInfo(@Query("type")type:String):Call<PlacesBean>
}