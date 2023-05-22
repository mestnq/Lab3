package com.example.lab3.repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private const val url = "https://develtop.ru/study/"
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val cardsApi: CardsApi = getRetrofit().create(CardsApi::class.java)
}