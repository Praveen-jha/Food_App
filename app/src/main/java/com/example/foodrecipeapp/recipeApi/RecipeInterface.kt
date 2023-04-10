package com.example.foodrecipeapp.recipeApi

import com.example.foodrecipeapp.models.RecipeResponse
import retrofit2.Response
import retrofit2.http.GET

interface RecipeInterface {

    @GET("categories.php")
    suspend fun getAllRecipes(): Response<RecipeResponse>
}