package com.android.movieappcompose.module.popular.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.movieappcompose.module.base.BaseViewModel
import com.android.movieappcompose.network_utils.LoadingStatus
import com.android.movieappcompose.repository.MovieRepository
import com.android.movieappcompose.repository.Result
import com.android.movieappcompose.rest.data.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel
@Inject constructor(private val movieRepository: MovieRepository) :
    BaseViewModel() {

    private var _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = _movies


    fun getPopularTvShows() {
        movieRepository.getPopularTvShows()
            .subscribeBy {
                when (it) {
                    is Result.Success -> {
                        _movies.value = it.data.movies
                    }

                    is Result.Error -> {
                        _loadingState.value = LoadingStatus.Error(it.errorCode, it.errorMessage)
                    }
                }
            }.run {
                disposeBag.add(this)
            }
    }

}