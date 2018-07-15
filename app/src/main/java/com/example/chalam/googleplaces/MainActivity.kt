package com.example.chalam.googleplaces

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.chalam.googleplaces.beans.PlacesBean
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        search.setOnClickListener({

            var r = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("https://maps.googleapis.com/").build()
            var api = r.create(GooglePlacesAPI::class.java)

            var call = api.getPlacesInfo(spinner.selectedItem.toString())

            call.enqueue(object:Callback<PlacesBean>{
                override fun onFailure(call: Call<PlacesBean>?, t: Throwable?) {
                    Toast.makeText(this@MainActivity,"Failure...",Toast.LENGTH_LONG).show()
                    Log.e("msg : ",t!!.message)
                }

                override fun onResponse(call: Call<PlacesBean>?, response: Response<PlacesBean>?) {
                    var res = response!!.body()

                    var list = mutableListOf<String>()
                    for(x in res!!.results!!)
                    {
                        list.add(""+x.name+"\n"+x.vicinity)
                    }
                    var adapter = ArrayAdapter<String>(this@MainActivity,android.R.layout.simple_list_item_1,list)
                    listView.adapter = adapter
                }

            })
        })
    }
}
