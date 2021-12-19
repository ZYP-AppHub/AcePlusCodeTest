package com.zyp.codetest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zyp.codetest.api.ApiService

class MainViewModelFactory constructor(private val apiService: ApiService) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            MainViewModel(this.apiService) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}