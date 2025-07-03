package com.tops.firebasedemo.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

//    var service: RetrofitService? =null
//
//    fun getInstance(): RetrofitService{
//        if (service==null){
//            val retrofit: Retrofit= Retrofit.Builder()
//                .baseUrl("https://dummyjson.com/")
//                .addConverterFactory(GsonConverterFactory().create())
//                .build()
//
//            service = retrofit.create(RetrofitService::class.java)
//        }
//        return service!!
//
//    }

    private const val BASE_URL = "https://dummyjson.com/"

    // Built only once and reused
    val service: RetrofitService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitService::class.java)
    }
}