package com.tops.firebasedemo.services

import com.tops.firebasedemo.model.Recipe
import com.tops.firebasedemo.model.RecipeRoot
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {

    @GET("/recipes/1")
    fun getData(): Call<Recipe>
}