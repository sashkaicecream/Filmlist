package com.example.filmlist.di

import com.example.filmlist.BuildConfig
import com.example.filmlist.core.datasource.FilmsDataSource
import com.example.filmlist.core.domain.repository.FilmsRepository
import com.example.filmlist.data.local.FilmsLocalDataSource
import com.example.filmlist.data.remote.FilmsApi
import com.example.filmlist.data.remote.FilmsRemoteDataSource
import com.example.filmlist.data.remote.provideApi
import com.example.filmlist.ui.films.film_detailed.FilmDetailedViewModel
import com.example.filmlist.ui.films.filmlist.FilmsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val local = named("local")
val remote = named("remote")

val filmsModule = module {
    single { provideApi(FilmsApi::class.java, BuildConfig.API_BASE_URL, get()) }

    single<FilmsDataSource>(local) { FilmsLocalDataSource() }
    single<FilmsDataSource>(remote) { FilmsRemoteDataSource(get()) }

    single { FilmsRepository(get(local), get(remote)) }

    viewModel { FilmsViewModel(get()) }
    viewModel { FilmDetailedViewModel(get()) }
}
