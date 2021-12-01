package com.softradix.nearbysearch.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class MyViewModel : ViewModel(){
    var isLoading = MutableLiveData<Boolean>()
}