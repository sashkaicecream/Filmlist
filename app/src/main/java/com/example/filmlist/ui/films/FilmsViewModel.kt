package com.example.filmlist.ui.films

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filmlist.core.domain.models.Film
import com.example.filmlist.core.domain.repository.FilmsRepository
import com.github.michaelbull.result.fold
import kotlinx.coroutines.launch
import kotlin.Exception

class FilmsViewModel(private val filmsRepo: FilmsRepository) : ViewModel() {
    private val _films = MutableLiveData<List<Film>>()
    val films: LiveData<List<Film>> get() = _films

    private val _filmsException = MutableLiveData<Exception>()
    val filmsException: LiveData<Exception> get() = _filmsException

    fun getFilms() {
        viewModelScope.launch {
            filmsRepo.getFilms().fold(
                { _films.postValue(it) },
                { _filmsException.postValue(it) }
            )
        }
    }

}
