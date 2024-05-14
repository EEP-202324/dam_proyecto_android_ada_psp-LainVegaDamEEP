package com.example.teetech.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.teetech.repository.TShirtRepository
import com.example.teetech.model.TShirt
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import com.example.teetech.network.TShirtApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Response

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
}
