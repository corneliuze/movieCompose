package com.android.movieappcompose.repository

import com.android.movieappcompose.rest.MovieApiInterface
import com.android.movieappcompose.rest.data.Movies
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApiService: MovieApiInterface
) : MovieRepository {
    override fun searchTvShows(): Single<Result<Movies>> {
        return movieApiService.searchTopTvShow().toResult()
    }

    override fun discoverTvShows(): Single<Result<Movies>> {
        return movieApiService.discoverMovieShow().toResult()
    }

    override fun getTopRatedTvShows(): Single<Result<Movies>> {
        return movieApiService.getTopRatedTvShow().toResult()
    }

    override fun getPopularTvShows(): Single<Result<Movies>> {
        return movieApiService.getPopularTvShow().toResult()
    }
}