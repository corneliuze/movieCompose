package com.android.movieappcompose.repository

import com.android.movieappcompose.rest.data.Movies
import io.reactivex.Single
import retrofit2.Response

interface MovieRepository {

    fun searchTvShows() : Single<Result<Movies>>

    fun discoverTvShows() : Single<Result<Movies>>

    fun getTopRatedTvShows() : Single<Result<Movies>>

    fun getPopularTvShows() : Single<Result<Movies>>

}