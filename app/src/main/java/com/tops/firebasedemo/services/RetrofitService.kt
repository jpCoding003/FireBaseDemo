package com.tops.firebasedemo.services

import com.tops.firebasedemo.model.Recipe
import com.tops.firebasedemo.model.RecipeRoot
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {

    @GET("/recipes")
    fun getData(): Call<RecipeRoot>

    @DELETE("/recipes/{id}")
    fun onDeleteData(@Path("id") id: Int): Call<RecipeRoot>
}