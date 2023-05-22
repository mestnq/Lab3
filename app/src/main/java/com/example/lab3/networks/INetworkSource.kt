package com.example.lab3.networks

import com.example.lab3.models.CardRequest
import retrofit2.Response

interface INetworkSource {
    suspend fun getCards(): Response<List<CardRequest>>
}