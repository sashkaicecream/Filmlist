package com.example.filmlist.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _stage = MutableLiveData<ApplicationStage>(ApplicationStage.FilmsStage)
    val stage: LiveData<ApplicationStage> = _stage

    var prev: ApplicationStage? = null

    fun changeStage(next: ApplicationStage) {
        val current = stage.value
        if (next != current) {
            prev = current
            _stage.value = next
        }
    }
}
