package com.tops.firebasedemo.model


import com.google.gson.annotations.SerializedName

data class RecipeRoot(

    @SerializedName("recipes")
    val recipes: List<Recipe>
)