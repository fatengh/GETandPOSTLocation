package com.example.getandpostlocation

import com.example.getandpostlocation.DataItem
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {
    @GET("/test/")
    fun getUser():Call<Array<DataItem>>
    @POST("/test/")
    fun addUser(@Body userData:DataItem):Call<DataItem>

}