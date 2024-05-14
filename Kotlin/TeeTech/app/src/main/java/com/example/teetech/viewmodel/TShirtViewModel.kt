package com.example.teetech.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teetech.model.TShirt
import com.example.teetech.network.TShirtApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TShirtViewModel() : ViewModel() {
    private val _tShirts = MutableStateFlow<List<TShirt>>(emptyList())
    val tShirts: StateFlow<List<TShirt>> = _tShirts.asStateFlow()


    fun loadTShirts() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = TShirtApi.retrofitService.getTShirts()
            if (response.isSuccessful) {
                _tShirts.value = response.body()!!
            } else {
                Log.e("TShirtViewModel", response.errorBody().toString())
            }
        }
    }

    fun navigateToCreateTShirtScreen() {
        TODO("Not yet implemented")
    }

    fun createTShirt(tShirt: TShirt) {
// TODO: Implementar la creación de una camiseta
    }
}
