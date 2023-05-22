package com.example.lab3.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lab3.models.Card
import com.example.lab3.models.LoadingResult
import com.example.lab3.networks.ApiNetworkSource
import com.example.lab3.networks.INetworkSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ToolsFragmentViewModel : ViewModel() {
    val liveData = MutableLiveData<LoadingResult<List<Card>?>>()
    private val network: INetworkSource = ApiNetworkSource()
    fun init() {
        viewModelScope.launch {
            val response = withContext(Dispatchers.IO) {
                network.getCards()
            }
            if (response.isSuccessful) {
                val responses = response.body()
                val cards = responses?.let { Card.getTools(it) }
                val result = LoadingResult.Success(cards)
                liveData.postValue(result)
            } else {
                liveData.postValue(LoadingResult.Failure("internet error", "check your internet connection"))
            }
        }
    }
}