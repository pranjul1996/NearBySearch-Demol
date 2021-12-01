package com.softradix.nearbysearch.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class MyViewModel : ViewModel(){
    val apiError = MutableLiveData<String>()
    val onFailure = MutableLiveData<Throwable>()
    var isLoading = MutableLiveData<Boolean>()
}