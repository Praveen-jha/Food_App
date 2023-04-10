package com.example.foodrecipeapp.recipeApi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RecipeUtilities {


    private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

    fun getInstance() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}