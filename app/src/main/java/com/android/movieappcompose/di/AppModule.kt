package com.android.movieappcompose.di

import com.android.movieappcompose.repository.MovieRepository
import com.android.movieappcompose.repository.MovieRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {

    @Binds
    abstract fun bindMovieInterface(movieRepositoryImpl: MovieRepositoryImpl) : MovieRepository

}