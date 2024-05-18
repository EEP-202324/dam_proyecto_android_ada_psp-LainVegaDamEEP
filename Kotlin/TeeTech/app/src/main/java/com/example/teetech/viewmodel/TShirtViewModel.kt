package com.example.teetech.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teetech.model.TShirt
import com.example.teetech.network.TShirtApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.asStateFlow

class TShirtViewModel : ViewModel() {
    private val _tShirts = MutableStateFlow<List<TShirt>>(emptyList())
    val tShirts: StateFlow<List<TShirt>> = _tShirts.asStateFlow()

    init {
        loadTShirts()  // Carga inicial de camisetas
    }

    fun createTShirt(tShirt: TShirt) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = TShirtApi.retrofitService.createTShirt(tShirt)
            if (response.isSuccessful) {
                loadTShirts()  // Recargar lista después de crear una camiseta
            } else {
                Log.e("TShirtViewModel", "Failed to create t-shirt: ${response.errorBody()?.string()}")
            }
        }
    }

    fun loadTShirts() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = TShirtApi.retrofitService.getTShirts()
            if (response.isSuccessful && response.body() != null) {
                _tShirts.value = response.body()!!
            } else {
                Log.e("TShirtViewModel", "Failed to load t-shirts: ${response.errorBody()?.string()}")
            }
        }
    }

    fun deleteTShirt(tShirtId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = TShirtApi.retrofitService.deleteTShirt(tShirtId)
            if (response.isSuccessful) {
                loadTShirts()  // Recargar lista después de eliminar una camiseta
            } else {
                Log.e("TShirtViewModel", "Failed to delete t-shirt: ${response.errorBody()?.string()}")
            }
        }
    }
}
