package com.tops.firebasedemo.model


import com.google.gson.annotations.SerializedName

data class Recipe(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("ingredients")
    val ingredients: List<String>,
    @SerializedName("instructions")
    val instructions: List<String>,
    @SerializedName("name")
    val name: String
)

