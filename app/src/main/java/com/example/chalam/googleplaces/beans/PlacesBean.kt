package com.example.chalam.googleplaces.beans

import com.google.gson.annotations.SerializedName

data class PlacesBean(@SerializedName("results")
                      val results: List<ResultsItem>?,
                      @SerializedName("status")
                      val status: String = "")