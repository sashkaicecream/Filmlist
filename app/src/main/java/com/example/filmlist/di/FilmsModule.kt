package com.example.filmlist.di

import com.example.filmlist.core.datasource.FilmsDataSource
import com.example.filmlist.core.domain.repository.FilmsRepository
import com.example.filmlist.data.local.FilmsLocalDataSource
import com.example.filmlist.data.remote.FilmsRemoteDataSource
import com.example.filmlist.ui.films.FilmsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val local = named("local")
val remote = named("remote")

val filmsModule = module {
    single<FilmsDataSource>(local) { FilmsLocalDataSource() }
    single<FilmsDataSource>(remote) { FilmsRemoteDataSource() }

    single { FilmsRepository(get(local), get(remote)) }

    viewModel { FilmsViewModel(get()) }
}
