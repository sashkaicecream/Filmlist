package com.example.filmlist.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.filmlist.ui.ApplicationStage.*

class MainViewModel : ViewModel() {
    private val _stage = MutableLiveData<ApplicationStage>(FilmsStage)
    val stage: LiveData<ApplicationStage> = _stage
    var prev: ApplicationStage? = null

    fun changeStage(next: ApplicationStage) {
        val current = stage.value
        if (next != current) {
            prev = current
            _stage.value = next
        }
    }

    fun back() {
        val next = when(prev) {
            is LikedFilmsStage -> LikedFilmsStage
            is FilmsStage -> FilmsStage
            else -> Cancel
        }

        prev = _stage.value
        _stage.value = next
    }
}
