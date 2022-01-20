package com.example.challenge3fragmentsbootcamp.main

import android.util.Log
import androidx.lifecycle.*
import com.example.challenge3fragmentsbootcamp.Crypto
import com.example.challenge3fragmentsbootcamp.api.ApiResponseStatus
import kotlinx.coroutines.*
import java.net.UnknownHostException

private val TAG = MainViewModel::class.java.simpleName
class MainViewModel: ViewModel() {
    private val repository = MainRepository()

    private val _status = MutableLiveData<ApiResponseStatus>()
    val status: LiveData<ApiResponseStatus>
        get() = _status

    private var _krList = MutableLiveData<MutableList<Crypto>>()
    val krList: LiveData<MutableList<Crypto>>
        get() = _krList

    init {
        reloadCryptos()
    }

    private fun reloadCryptos() {
        viewModelScope.launch {
            try {
                _status.value = ApiResponseStatus.LOADING
                _krList.value = repository.fetchCryptos()
                _status.value = ApiResponseStatus.DONE
            } catch (e: UnknownHostException) {
                _status.value = ApiResponseStatus.ERROR
                Log.d(TAG, "No internet connection.", e)
            }
        }
    }
}