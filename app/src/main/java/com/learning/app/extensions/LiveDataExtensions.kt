package com.learning.app.extensions

import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<T>.notifyObservers() {
    postValue(this.value)
}