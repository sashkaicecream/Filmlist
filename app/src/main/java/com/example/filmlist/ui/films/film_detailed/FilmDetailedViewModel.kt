package com.example.filmlist.ui.films.film_detailed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filmlist.core.domain.models.FilmDetailed
import com.example.filmlist.core.domain.repository.FilmsRepository
import com.github.michaelbull.result.fold
import kotlinx.coroutines.launch

class FilmDetailedViewModel(private val filmsRepo: FilmsRepository) : ViewModel() {
    private val _film = MutableLiveData<FilmDetailed>()
    val film: LiveData<FilmDetailed> get() = _film

    private val _filmException = MutableLiveData<Exception>()
    val filmException: LiveData<Exception> get() = _filmException

    fun getFilmDetailed(id: String) {
        viewModelScope.launch {
            filmsRepo.getFilmDetailed(id).fold(
                { _film.postValue(it) },
                { _filmException.postValue(it) },
            )
        }
    }

    fun changeFilmLike(id: String, newState: Boolean) {
        viewModelScope.launch {
            filmsRepo.changeLikeState(id, newState)
        }
    }
}
