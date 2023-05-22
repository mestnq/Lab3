package com.example.lab3.repository

import com.example.lab3.models.CardRequest
import retrofit2.Response
import retrofit2.http.GET

interface CardsApi {
    @GET("new_text.json")
    suspend fun getCards(): Response<List<CardRequest>>
}