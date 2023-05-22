package com.example.lab3.networks

import com.example.lab3.models.CardRequest
import com.example.lab3.repository.RetrofitBuilder
import com.example.lab3.networks.INetworkSource
import retrofit2.Response

class ApiNetworkSource: INetworkSource {

    override suspend fun getCards(): Response<List<CardRequest>> {
        return RetrofitBuilder.cardsApi.getCards()
    }
}