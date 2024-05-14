package com.example.teetech.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.teetech.repository.TShirtRepository

//class TShirtViewModelFactory(private val repository: TShirtRepository) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(TShirtViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return TShirtViewModel(repository) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}
